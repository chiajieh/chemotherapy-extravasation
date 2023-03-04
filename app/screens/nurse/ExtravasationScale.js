import { ReactNativeZoomableView } from "@openspacelabs/react-native-zoomable-view";
import { Image } from "native-base";
import React from "react";

function ExtravasationScale() {
  return (
    <ReactNativeZoomableView maxZoom={30} minZoom={1} initialZoom={1}>
      <Image
        source={require("../../assets/ExtravasationScale.png")}
        alt="Extravasation Scale"
        width="100%"
        height="100%"
        resizeMode="contain"
      />
    </ReactNativeZoomableView>
  );
}

export default ExtravasationScale;
