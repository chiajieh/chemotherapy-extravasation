import { Center, Image, ScrollView, Text } from "native-base";
import React from "react";

function FollowUp({ route }) {
  const { item } = route.params;

  return (
    <ScrollView p={4} space={1}>
      <Text fontWeight="medium" fontSize="xl" pb="2">
        {item.name}
      </Text>
      <Text fontSize="lg" color="coolGray.800">
        {"Follow up: " + item.followUpDate}
      </Text>
      <Text fontSize="lg" color="coolGray.800">
        {"Appointment: " + item.appointmentDate}
      </Text>
      <Text fontSize="lg" color="coolGray.800">
        {"Chemotherapy: " + item.chemotherapy}
      </Text>
      <Text fontSize="lg" color="coolGray.800">
        {"Level: " + item.level}
      </Text>
      <Text fontSize="lg" color="coolGray.800">
        {"Symptoms: " + item.symptoms}
      </Text>
      <Text fontWeight="medium" fontSize="xl" pt="2">
        Photos
      </Text>
      <Center mb="3" w="100%" maxH="500">
        <Image
          source={require("../../assets/close_example.jpg")}
          alt="close_example"
          width="100%"
          height="100%"
          resizeMode="contain"
        />
      </Center>
      <Center mb="3" w="100%" maxH="500">
        <Image
          source={require("../../assets/far_example.jpg")}
          alt="far_example"
          width="100%"
          height="100%"
          resizeMode="contain"
        />
      </Center>
    </ScrollView>
  );
}

export default FollowUp;
