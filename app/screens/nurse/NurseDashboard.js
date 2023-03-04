import { Box, Center, Heading, VStack } from "native-base";
import React from "react";
import DashboardItem from "../../components/DashboardItem";

function NurseDashboard({ navigation }) {
  return (
    <Box px={4} mt="5%" flex={1}>
      <Heading size="md">Welcome name_placeholder</Heading>
      <Center>
        <VStack mt="12" space={6} alignItems="center" w="83%">
          <DashboardItem
            text="Chemotherapies"
            navigateTo="Chemotherapies"
            navigation={navigation}
          />
        </VStack>
      </Center>
    </Box>
  );
}

export default NurseDashboard;
