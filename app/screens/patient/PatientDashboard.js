import { Box, Center, Heading, VStack } from "native-base";
import React from "react";
import DashboardItem from "../../components/DashboardItem";

function PatientDashboard({ navigation }) {
  return (
    <Box px={4} mt="5%" flex={1}>
      <Heading size="md">Welcome placeholder_name</Heading>
      <Center>
        <VStack mt="12" space={6} alignItems="center" w="83%">
          <DashboardItem
            text="General Information"
            navigateTo="GeneralInformation"
            navigation={navigation}
          />
          <DashboardItem
            text="Home Treatment"
            navigateTo="HomeTreatment"
            navigation={navigation}
          />
          <DashboardItem
            text="Patient Assessments"
            navigateTo="PatientAssessments"
            navigation={navigation}
          />
        </VStack>
      </Center>
    </Box>
  );
}

export default PatientDashboard;