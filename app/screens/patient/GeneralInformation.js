import { Ionicons } from "@expo/vector-icons";
import {
  Center,
  FlatList,
  Icon,
  Input,
  Pressable,
  Text,
  VStack,
} from "native-base";
import React from "react";

const DATA = [
  { name: "Extravasation: The leakage of blood, lymph, or other fluid, such as an anticancer drug, from a blood vessel or tube into the tissue around it. It is also used to describe the movement of cells out of a blood vessel into tissue during inflammation or metastasis (the spread of cancer)." },
  { name: "Vesicant: The leakage of certain drugs called vesicants out of a vein into the tissue around it. Vesicants cause blistering and other tissue injury that may be severe and can lead to tissue necrosis (tissue death)." },
];

export default function Definitions({ navigation, onPress }) {
  const [Data, setData] = React.useState(DATA);

  function defaultOnPress(item) {
    navigation.navigate("GeneralInformation", {
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
