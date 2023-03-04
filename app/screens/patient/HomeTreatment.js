import {
  Center,
  FlatList,
  Pressable,
  Text,
  VStack,
} from "native-base";
import React from "react";

const DATA = [
  { name: "Chemotherapy: " },
  { name: "IV Site: " },
  { name: "Antidote: " },
  { name: "Intervention instructions: "},
];

export default function HomeTreatment({ navigation, onPress }) {
  const [Data, setData] = React.useState(DATA);

  function defaultOnPress(item) {
    navigation.navigate("HomeTreatment", {
      name: item.name,
    });
  }

  if (!onPress) {
    onPress = defaultOnPress;
  }

  return (
    <VStack px={4} mt="5%">
      <FlatList
        data={Data}
        keyExtractor={(item) => item.name}
        renderItem={({ item }) => (
          <Pressable onPress={() => onPress(item)} w="100%">
            {({ isPressed }) => {
              return (
                <Center
                  style={{
                    transform: [
                      {
                        scale: isPressed ? 0.96 : 1,
                      },
                    ],
                  }}
                  p="5"
                  rounded="2xl"
                  borderColor="coolGray.300"
                  borderBottomWidth="1"
                  width="85%"
                  alignSelf="center"
                >
                  <Text fontWeight="medium" fontSize="lg" color="coolGray.800">
                    {item.name}
                  </Text>
                </Center>
              );
            }}
          </Pressable>
        )}
      />
    </VStack>
  );
}
