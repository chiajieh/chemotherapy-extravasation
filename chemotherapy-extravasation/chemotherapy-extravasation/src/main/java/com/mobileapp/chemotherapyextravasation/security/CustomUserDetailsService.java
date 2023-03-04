package com.mobileapp.chemotherapyextravasation.security;


import com.mobileapp.chemotherapyextravasation.entity.Nurse;
import com.mobileapp.chemotherapyextravasation.entity.Patient;
import com.mobileapp.chemotherapyextravasation.repository.NurseRepository;
import com.mobileapp.chemotherapyextravasation.repository.PatientRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private NurseRepository nurseRepository;
    private PatientRepository patientRepository;

    public CustomUserDetailsService(NurseRepository nurseRepository, PatientRepository patientRepository) {
        this.nurseRepository = nurseRepository;
        this.patientRepository = patientRepository;
    }

    /**
     * Method to load the user object from the database based on the role
     * @param usernameOrEmail
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = null;
        String role = null;

        // Check if the user is a Nurse
        Optional<Nurse> nurseOptional = nurseRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        if (nurseOptional.isPresent()) {
            Nurse nurse = nurseOptional.get();
            role = "ROLE_NURSE";
            user = new User(nurse.getEmail(), nurse.getPassword(),
                    true, true, true, true, getAuthorities(role));
        } else {
            // Check if the user is a Patient
            Optional<Patient> patientOptional = patientRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
            if (patientOptional.isPresent()) {
                Patient patient = patientOptional.get();
                role = "ROLE_PATIENT";
                user = new User(patient.getEmail(), patient.getPassword(),
                        true, true, true, true, getAuthorities(role));
            } else {
                throw new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail);
            }
        }
        return user;
    }

    /**
     * Method to get authorities based on the role
     * @param role
     * @return
     */
    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}