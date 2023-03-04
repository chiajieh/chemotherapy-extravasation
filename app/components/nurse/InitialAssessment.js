import {
  CheckIcon,
  FormControl,
  Select,
  TextArea,
  VStack,
  WarningOutlineIcon,
} from "native-base";
import React from "react";

function InitialAssessment({ data, setData }) {
  return (
    <VStack space={5} pb="3" alignItems="center">
      <FormControl w="75%" isRequired>
        <FormControl.Label>Choose Level</FormControl.Label>
        <Select
          accessibilityLabel="Choose Level"
          placeholder="Choose Level"
          _selectedItem={{
            bg: "teal.600",
            endIcon: <CheckIcon size={5} />,
          }}
          mt="1"
          selectedValue={data.level}
          onValueChange={(itemValue) =>
            setData((prev) => ({ ...prev, level: itemValue }))
          }
        >
          <Select.Item label="Normal" value="Normal" />
          <Select.Item label="Mild" value="Mild" />
          <Select.Item label="Moderate" value="Moderate" />
          <Select.Item label="Severe" value="Severe" />
        </Select>
        <FormControl.ErrorMessage leftIcon={<WarningOutlineIcon size="xs" />}>
          Please make a selection!
        </FormControl.ErrorMessage>
      </FormControl>
      <FormControl w="75%" maxW="300" isRequired>
        <FormControl.Label>Signs and Symptoms</FormControl.Label>
        <TextArea
          h="150"
          placeholder="Enter Signs and Symptoms"
          value={data.symptoms}
          onChangeText={(text) =>
            setData((prev) => ({ ...prev, symptoms: text }))
          }
        />
      </FormControl>
    </VStack>
  );
}

export default InitialAssessment;
