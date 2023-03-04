import { Heading, HStack, Text, VStack } from "native-base";
import React from "react";

function ChemotherapyInformation({ item, summary = true }) {
  return (
    <VStack p={4} space={3}>
      <Heading pb="1">{item.name}</Heading>
      <HStack alignItems="center">
        <Text fontSize="lg" bold>
          {"Type: "}
        </Text>
        <Text fontSize="lg">{item.type}</Text>
      </HStack>
      <HStack alignItems="center">
        <Text fontSize="lg" bold>
          {"Antidote: "}
        </Text>
        <Text fontSize="lg">{item.antidote ? item.antidote : "None"}</Text>
      </HStack>
      <VStack space={1}>
        <Heading size="md">Intervention</Heading>
        <Text>{item.intervention}</Text>
      </VStack>
      {!summary && <Text></Text>}
    </VStack>
  );
}

export default ChemotherapyInformation;
