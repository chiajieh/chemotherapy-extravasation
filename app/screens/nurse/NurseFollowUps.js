import { FlatList, Pressable, Text, VStack } from "native-base";
import React from "react";

const DATA = [
  {
    id: 3,
    name: "John Doe",
    appointmentDate: "March 3, 2023",
    followUpDate: "March 4, 2023",
    chemotherapy: "Mitomycin",
    level: "Mild",
    symptoms: "Mild pain",
    image: "../../assets/normal_example.png",
  },
  {
    id: 2,
    name: "Jane Doe",
    appointmentDate: "February 20, 2023",
    followUpDate: "February 27, 2023",
    chemotherapy: "PACLltaxel",
    level: "Mild",
    symptoms: "No pain",
    image: "../../assets/normal_example.png",
  },
  {
    id: 1,
    name: "Jane Doe",
    appointmentDate: "February 20, 2023",
    followUpDate: "February 21, 2023",
    chemotherapy: "PACLltaxel",
    level: "Mild",
    symptoms: "Mild pain",
    image: "../../assets/normal_example.png",
  },
];

function NurseFollowUps({ navigation }) {
  function handlePress(item) {
    navigation.navigate("Follow Up", {
      item,
    });
  }

  return (
    <FlatList
      data={DATA}
      keyExtractor={(item) => item.id}
      renderItem={({ item }) => (
        <Pressable onPress={() => handlePress(item)} w="100%">
          {({ isPressed }) => {
            return (
              <VStack
                style={{
                  transform: [
                    {
                      scale: isPressed ? 0.96 : 1,
                    },
                  ],
                }}
                p="5"
                borderColor="coolGray.300"
                borderBottomWidth="1"
                width="85%"
                alignSelf="center"
              >
                <Text fontWeight="medium" fontSize="lg" color="coolGray.800">
                  {item.name}
                </Text>
                <Text fontSize="lg" color="coolGray.800">
                  {"Follow up: " + item.followUpDate}
                </Text>
                <Text fontSize="lg" color="coolGray.800">
                  {"Appointment: " + item.appointmentDate}
                </Text>
              </VStack>
            );
          }}
        </Pressable>
      )}
    />
  );
}

export default NurseFollowUps;
