import { Center, Pressable, Text } from "native-base";
import React from "react";

function DashboardItem({ text, navigateTo, navigation }) {
  return (
    <Pressable onPress={() => navigation.navigate(navigateTo)} w="100%">
      {({ isHovered, isPressed }) => {
        return (
          <Center
            bg={
              isPressed
                ? "coolGray.200"
                : isHovered
                ? "coolGray.200"
                : "coolGray.100"
            }
            style={{
              transform: [
                {
                  scale: isPressed ? 0.96 : 1,
                },
              ],
            }}
            p="5"
            rounded="xl"
            shadow={3}
            borderWidth="1"
            borderColor="coolGray.300"
          >
            <Text fontWeight="medium" fontSize="xl" color="coolGray.800">
              {text}
            </Text>
          </Center>
        );
      }}
    </Pressable>
  );
}

export default DashboardItem;
