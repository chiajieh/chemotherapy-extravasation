import {
  Box,
  Button,
  Heading,
  HStack,
  Image,
  ScrollView,
  VStack,
} from "native-base";
import React, { useState } from "react";
import ChemotherapyInformation from "../../components/ChemotherapyInformation";
import InitialAssessment from "../../components/nurse/InitialAssessment";
import ScheduleFollowUp from "../../components/nurse/ScheduleFollowUp";
import TakePicture from "../../components/TakePicture";
import Chemotherapies from "./Chemotherapies";

const initialData = {
  chemotherapy: "",
  level: "",
  symptoms: "",
  startDate: new Date(),
  patientEmail: "",
};

function StartPatientPathway({ navigation }) {
  const [data, setData] = useState(initialData);
  const [screenNum, setScreenNum] = useState(1);

  //const { state } = React.useContext(AuthContext);

  function renderSwitch(param) {
    switch (param) {
      case 1:
        return (
          <VStack h="85%">
            <Heading pt="3" pl="3" size="lg">
              Select Chemotherapy
            </Heading>
            <Chemotherapies
              onPress={(item) => (
                setData({
                  ...initialData,
                  chemotherapy: item,
                  startDate: new Date(),
                }),
                setScreenNum(2)
              )}
            />
          </VStack>
        );
      case 2:
        return (
          <ScrollView>
            <ChemotherapyInformation item={data.chemotherapy} summary={false} />
            {renderButtons()}
          </ScrollView>
        );
      case 3:
        return (
          <VStack>
            <Heading p="3" size="lg">
              Take close-up picture of Extravasation
            </Heading>
            <Box h="520">
              <TakePicture />
            </Box>
            {renderButtons()}
          </VStack>
        );
      case 4:
        return (
          <VStack>
            <Heading p="3" size="lg">
              Take full picture of Extravasation
            </Heading>
            <Box h="520">
              <TakePicture />
            </Box>
            {renderButtons()}
          </VStack>
        );
      case 5:
        return (
          <ScrollView>
            <Heading pt="3" pl="3" size="lg">
              Initial Assessment
            </Heading>
            <Box mt="3" mb="3" w="100%" h="500">
              <Image
                source={require("../../assets/ExtravasationScale.png")}
                alt="Extravasation Scale"
                width="100%"
                height="100%"
                resizeMode="contain"
              />
            </Box>
            <InitialAssessment data={data} setData={setData} />
            {renderButtons()}
          </ScrollView>
        );
      case 6:
        return (
          <ScrollView>
            <Heading pt="3" pl="3" size="lg">
              Follow Up
            </Heading>
            <ScheduleFollowUp data={data} setData={setData} />
            {renderButtons("Back", "Finish")}
          </ScrollView>
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
      const body = { ...data, chemotherapy: data.chemotherapy.name };
      body.startDate = new Date(body.startDate).toLocaleDateString();

      console.log(body);

      //go to home screen
      navigation.navigate("Home");
    }
  }

  function renderButtons(back = "Back", next = "Next") {
    return (
      <HStack mt="6" mb="12" justifyContent="space-around">
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

export default StartPatientPathway;
