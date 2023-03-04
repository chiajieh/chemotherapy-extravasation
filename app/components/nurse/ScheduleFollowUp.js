import { AntDesign } from "@expo/vector-icons";
import moment from "moment/moment";
import {
  FormControl,
  HStack,
  IconButton,
  Input,
  Text,
  VStack,
} from "native-base";
import React, { useState } from "react";
import DateTimePickerModal from "react-native-modal-datetime-picker";

function ScheduleFollowUp({ data, setData }) {
  const [isDatePickerVisible, setDatePickerVisibility] = useState(false);

  function handleConfirm(date) {
    setData((prev) => ({ ...prev, startDate: date }));
    setDatePickerVisibility(false);
  }

  function displayFollowUp(label = "", number = 0, type = "w") {
    return (
      <Text fontSize="md">
        {label}
        {" - "}
        {moment(data.startDate).add(number, type).format("dddd, MMMM Do")}
      </Text>
    );
  }

  return (
    <VStack p="5">
      <Text bold fontSize="lg">
        Appointment date:
      </Text>
      <HStack pb="3" alignItems="center">
        <Text fontSize="lg">
          {moment(data.startDate).format("dddd, MMMM Do")}
        </Text>
        <IconButton
          variant="ghost"
          onPress={() => setDatePickerVisibility(true)}
          _icon={{
            as: AntDesign,
            name: "calendar",
          }}
        />
        <DateTimePickerModal
          isVisible={isDatePickerVisible}
          mode="date"
          onConfirm={handleConfirm}
          onCancel={() => setDatePickerVisibility(false)}
        />
      </HStack>
      <Text bold fontSize="lg">
        Scheduled follow ups:
      </Text>
      {displayFollowUp("24 hrs", "1", "d")}
      {displayFollowUp("1 week", "1", "w")}
      {displayFollowUp("2 weeks", "2", "w")}
      {displayFollowUp("3 weeks", "3", "w")}
      {displayFollowUp("4 weeks", "4", "w")}
      {displayFollowUp("5 weeks", "5", "w")}
      {displayFollowUp("6 weeks", "6", "w")}

      <FormControl mt="5" w="75%" isRequired>
        <FormControl.Label>Patient Email</FormControl.Label>
        <Input
          placeholder="Patient Email"
          value={data.patientEmail}
          onChangeText={(text) =>
            setData((prev) => ({ ...prev, patientEmail: text }))
          }
        />
      </FormControl>
    </VStack>
  );
}

export default ScheduleFollowUp;
