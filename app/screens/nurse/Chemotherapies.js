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
import { AuthContext } from "../../components/AuthContext";

const DATA = [
  {
    id: 1,
    name: "PACLItaxel",
    type: "Vesicant",
    properties: null,
    antidote: null,
    intervention:
      "Apply cold pack for 15-20 minutes at least 4 times per day for the first 24-48 hours",
  },
  {
    id: 2,
    name: "Paclitaxel Protein bound",
    type: "Vesicant",
    properties: null,
    antidote: null,
    intervention:
      "Apply cold pack for 15-20 minutes at least 4 times per day for the first 24-48 hours",
  },
  {
    id: 3,
    name: "Dactinomycin",
    type: "Vesicant",
    properties: null,
    antidote: null,
    intervention:
      "Apply cold pack for 15-20 minutes at least 4 times per day for the first 24-48 hours",
  },
  {
    id: 4,
    name: "Mitomycin",
    type: "Vesicant",
    properties: null,
    antidote: null,
    intervention:
      "Apply cold pack for 15-20 minutes at least 4 times per day for the first 24-48 hours",
  },
];

export default function Chemotherapies({ navigation, onPress }) {
  //const { setData } = React.useContext(AuthContext);
  const [search, setSearch] = React.useState("");
  const [searchData, setsearchData] = React.useState(DATA);
  const { state } = React.useContext(AuthContext);

  function defaultOnPress(item) {
    navigation.navigate("ChemotherapyInformation", {
      item,
    });
  }

  if (!onPress) {
    onPress = defaultOnPress;
  }

  function handleSearch(text) {
    setSearch(text);

    const searchText = text.toUpperCase();

    const updatedData = searchData.filter((item) => {
      const item_data = `${item.name.toUpperCase()})`;
      return item_data.indexOf(searchText) > -1;
    });

    setsearchData(updatedData);
  }

  return (
    <VStack px={4} mt="5%">
      <Input
        alignSelf="center"
        fontSize="lg"
        value={search}
        onChangeText={(text) => handleSearch(text)}
        placeholder="Search"
        variant="filled"
        width="95%"
        borderRadius="xl"
        py="2"
        px="2"
        bg="gray.200"
        InputLeftElement={
          <Icon
            ml="2"
            size="lg"
            color="gray.400"
            as={<Ionicons name="ios-search" />}
          />
        }
      />
      <FlatList
        data={searchData}
        keyExtractor={(item) => item.id}
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
