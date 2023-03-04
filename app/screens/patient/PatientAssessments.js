import React, { useState } from "react";
import {
  Button,
  Center,
  Heading,
  HStack,
  Pressable,
  VStack,
  Text,
  FlatList,
  Input
} from "native-base";

const initialData = {
  assessments: ["2022-01-01", "2022-02-01", "2022-03-01", "2022-04-01", "2022-05-01", "2022-06-01", "2022-07-01"],
  selectedAssessments: "",
  symptoms: "",
};

function PatientAssessments() {
  const [data, setData] = useState(initialData);
  const [screenNum, setScreenNum] = useState(1);

  function renderSwitch(param) {
    switch (param) {
      case 1:
        return (
          <VStack px={4} mt="5%">
            <Heading pt="3" pl="3" size="lg">
              Upcoming assessments
            </Heading>
              <FlatList
                data={data.assessments}
                keyExtractor={(item) => item}
                renderItem={({ item }) => (
                  <Pressable onPress={() => {
                    setData({
                      ...data,
                      selectedAssessments: item
                    });
                    handleNext()}} w="100%">
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
                            {item}
                          </Text>
                        </Center>
                      );
                    }}
                  </Pressable>
                )}
              />
            </VStack>
        );
      case 2:
        return (
          <VStack px={4} mt="5%">
            <Heading pt="3" pl="3" size="lg">
              Please enter any symptoms:
            </Heading>
            <Input
              mt="3"
              placeholder="Enter your symptoms"
              value={data.symptoms}
              onChangeText={(text) =>
                setData({ ...data, symptoms: text })
              }
            />
            {renderButtons()}
          </VStack>
        );
        case 3:
          return (
            <VStack>
              <Heading>
              Take close-up of Extravasation
              </Heading>
              {renderButtons() }
            </VStack>
          );
      case 4:
        return (
          <VStack>
            <Heading>
            Take wide-shot of Extravasation
            </Heading>
            {renderButtons() }
          </VStack>
        );
      case 5:
        return (
          <VStack px={4} mt="5%">
            <Heading pt="3" pl="3" size="lg">
              Thank you!
            </Heading>
            <Text></Text>
            <Text>Your follow-up assessment is now complete. Your nurse will be notified.</Text>
          </VStack>
        );
      default:
        return "";
    }
  }

  function handleBack() {
    if (screenNum > 1) {
      setScreenNum((prev) => prev - 1);
    }
  }

  function handleNext() {
    if (screenNum <= 5) {
      setScreenNum((prev) => prev + 1);
    } else {
      //submit data to server
      const body = { ...data };

      console.log(body);
    }
  }

  function renderButtons(back = "Back", next = "Next") {
    return (
      <HStack mt="6" mb="10" justifyContent="space-around">
        <Button colorScheme="pink" w="16" onPress={() => handleBack()}>
          {back}
        </Button>
        <Button colorScheme="green" w="16" onPress={() => handleNext()}>
          {next}
        </Button>
      </HStack>
    );
  }

  return <>{renderSwitch(screenNum)}</>;
}

export default PatientAssessments;