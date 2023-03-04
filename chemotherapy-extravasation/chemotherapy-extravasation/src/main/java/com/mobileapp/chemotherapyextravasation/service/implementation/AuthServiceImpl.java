package com.mobileapp.chemotherapyextravasation.service.implementation;


import com.mobileapp.chemotherapyextravasation.dto.LoginDto;
import com.mobileapp.chemotherapyextravasation.dto.RegisterDto;
import com.mobileapp.chemotherapyextravasation.entity.Nurse;
import com.mobileapp.chemotherapyextravasation.entity.Patient;
import com.mobileapp.chemotherapyextravasation.entity.Role;
import com.mobileapp.chemotherapyextravasation.exception.ChemotherapyExtravasationAPIException;
import com.mobileapp.chemotherapyextravasation.exception.EmailAlreadyExistsException;
import com.mobileapp.chemotherapyextravasation.repository.NurseRepository;
import com.mobileapp.chemotherapyextravasation.repository.PatientRepository;
import com.mobileapp.chemotherapyextravasation.repository.RoleRepository;
import com.mobileapp.chemotherapyextravasation.security.JwtTokenProvider;
import com.mobileapp.chemotherapyextravasation.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private NurseRepository nurseRepository;
    private PatientRepository patientRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                                     NurseRepository nurseRepository,
                                     PatientRepository patientRepository,
                                     RoleRepository roleRepository,
                                     PasswordEncoder passwordEncoder,
                                     JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.nurseRepository = nurseRepository;
        this.patientRepository = patientRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        // store authentication object in a security context folder
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }



    @Override
    public String register(RegisterDto registerDto) {
        // Check if the user is registering as a Nurse or Patient
        String role = registerDto.getRole();
        if (role.equalsIgnoreCase("nurse")) {
            // Check if the username already exists in the Nurse repository
            if (nurseRepository.existsByUsername(registerDto.getUsername())) {
                throw new ChemotherapyExtravasationAPIException("Username already exists!");
            }
            // Check if the email already exists in the Nurse repository
            if (nurseRepository.existsByEmail(registerDto.getEmail())) {
                throw new EmailAlreadyExistsException("Email already exists!");
            }

            // Check if the username already exists in the Patient repository
            if (patientRepository.existsByUsername(registerDto.getUsername())) {
                throw new ChemotherapyExtravasationAPIException("Username already exists!");
            }
            // Check if the email already exists in the Patient repository
            if (patientRepository.existsByEmail(registerDto.getEmail())) {
                throw new EmailAlreadyExistsException("Email already exists!");
            }

            // Create a new Nurse object and save it to the Nurse repository
            Nurse nurse = new Nurse();
            nurse.setFirstName(registerDto.getFirstName());
            nurse.setLastName(registerDto.getLastName());
            nurse.setUsername(registerDto.getUsername());
            nurse.setEmail(registerDto.getEmail());
            nurse.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            nurseRepository.save(nurse);

            // Get the "ROLE_NURSE" from the Role repository and add it to the Nurse's roles
            Set<Role> roles = new HashSet<>();
//            Role nurseRole = roleRepository.findByName("ROLE_NURSE").get();
            Role nurseRole = roleRepository.findByName("ROLE_NURSE").orElse(null);
            roles.add(nurseRole);
            nurse.setRoles(roles);
            nurseRepository.save(nurse);

        } else if (role.equalsIgnoreCase("patient")) {
            // Check if the username already exists in the Patient repository
            if (patientRepository.existsByUsername(registerDto.getUsername())) {
                throw new ChemotherapyExtravasationAPIException("Username already exists!");
            }
            // Check if the email already exists in the Patient repository
            if (patientRepository.existsByEmail(registerDto.getEmail())) {
                throw new EmailAlreadyExistsException("Email already exists!");
            }
            // Check if the username already exists in the Nurse repository
            if (nurseRepository.existsByUsername(registerDto.getUsername())) {
                throw new ChemotherapyExtravasationAPIException("Username already exists!");
            }
            // Check if the email already exists in the Nurse repository
            if (nurseRepository.existsByEmail(registerDto.getEmail())) {
                throw new EmailAlreadyExistsException("Email already exists!");
            }

            // Create a new Patient object and save it to the Patient repository
            Patient patient = new Patient();
            patient.setFirstName(registerDto.getFirstName());
            patient.setLastName(registerDto.getLastName());
            patient.setUsername(registerDto.getUsername());
            patient.setEmail(registerDto.getEmail());
            patient.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            patientRepository.save(patient);

            // Get the "ROLE_PATIENT" from the Role repository and add it to the Patient's roles
            Set<Role> roles = new HashSet<>();
//            Role patientRole = roleRepository.findByName("ROLE_PATIENT").get();
            Role patientRole = roleRepository.findByName("ROLE_PATIENT").orElse(null);
            roles.add(patientRole);
            patient.setRoles(roles);
            patientRepository.save(patient);
        } else {
            throw new ChemotherapyExtravasationAPIException("Invalid role specified!");
        }
        return "User registered successfully!";
    }
}

