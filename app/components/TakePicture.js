import React, { useState, useRef, useEffect} from 'react';
import { StyleSheet, Text, View, TouchableOpacity} from 'react-native';
import { Camera } from 'expo-camera';

function TakePicture() {
  const [hasPermission, setHasPermission] = useState(null);
  const [type, setType] = useState(Camera.Constants.Type.back);
  const cameraRef = useRef(null);
 
  useEffect(() => {
      (async () => {
        const { status } = await Camera.requestCameraPermissionsAsync();
        setHasPermission(status === 'granted');
      })();
    }, []); 

  if (hasPermission === null) {
      return <View />;
    }

  if (hasPermission === false) {
    return <Text>No access to camera</Text>;
  }

  return (
    <View style={styles.container}>
      <View style={styles.cameraContainer}>
        <Camera 
          ref={cameraRef} 
          style={styles.camera} 
          type={type} 
          ratio={'1:1'} 
        />
      </View>

     
        <TouchableOpacity
          style={{
            alignSelf: 'flex-end',
            position: 'absolute',
            top: 400,
            left: 20,
            borderRadius: 50,
            backgroundColor: '#fff',
            padding: 15,
          }}
          onPress={async () => {
            if (cameraRef.current) {
              let photo = await cameraRef.current.takePictureAsync();
              console.log(photo);
            }
          }}
        >
          <Text style={{ fontSize: 18 }}>Take Picture</Text>
        </TouchableOpacity>
   
        <TouchableOpacity
          style={{
            alignSelf: 'flex-end',
            position: 'absolute',
            top: 400,
            right: 20,
            borderRadius: 50,
            backgroundColor: '#fff',
            padding: 15,
          }}
          onPress={() => {
            setType(
              type === Camera.Constants.Type.back
                ? Camera.Constants.Type.front
                : Camera.Constants.Type.back
            );
          }}
        >
          <Text style={{ fontSize: 18 }}>Flip Camera</Text>
        </TouchableOpacity>
      </View>
  );
}

  const styles = StyleSheet.create({
    container: {
      flex: 1,
      alignContent: 'center'
    },
    camera: {
      flex: 1,
      aspectRatio: 1,
    },
    cameraContainer: {
      flex: 1,
      flexDirection: 'row'
    },
    TouchableOpacity: {
      flex: 0.1,
      alignSelf: 'flex-end',
      alignItems: 'center',
    },
  });

export default TakePicture;


