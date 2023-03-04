import React from "react";
import ChemotherapyInformation from "../components/ChemotherapyInformation";

function ChemotherapyInfoScreen({ route }) {
  const { item } = route.params;

  return <ChemotherapyInformation item={item} summary={false} />;
}

export default ChemotherapyInfoScreen;
