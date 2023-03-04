import { NavigationContainer } from "@react-navigation/native";
import { createStackNavigator } from "@react-navigation/stack";
import { extendTheme, NativeBaseProvider } from "native-base";
import React from "react";
import { AuthContext } from "./components/AuthContext";
import NurseDashboard from "./screens/nurse/NurseDashboard";
import PatientDashboard from "./screens/patient/PatientDashboard";
import GeneralInformation from "./screens/patient/GeneralInformation";

import SignInScreen from "./screens/SignInScreen";
import SignUpScreen from "./screens/SignUpScreen";
import SplashScreen from "./screens/SplashScreen";

import ChemotherapyInfoScreen from "./screens/ChemotherapyInfoScreen";
import Chemotherapies from "./screens/nurse/Chemotherapies";
import ExtravasationScale from "./screens/nurse/ExtravasationScale";
import FollowUp from "./screens/nurse/FollowUp";
import NurseFollowUps from "./screens/nurse/NurseFollowUps";
import StartPatientPathway from "./screens/nurse/StartPatientPathway";

// navigation stack
const Stack = createStackNavigator();

export default function App() {
  // extend the theme
  const theme = extendTheme({
    config: {
      useSystemColorMode: false,
      initialColorMode: "light",
    },
  });

  const [state, dispatch] = React.useReducer(
    (prevState, action) => {
      switch (action.type) {
        case "RESTORE_TOKEN":
          return {
            ...prevState,
            userToken: action.token,
            userType: action.userType,
            isLoading: false,
          };
        case "SIGN_IN":
          return {
            ...prevState,
            isSignout: false,
            userToken: action.token,
            userType: action.userType,
          };
        case "SIGN_OUT":
          return {
            ...prevState,
            isSignout: true,
            userToken: null,
            userType: null,
          };
      }
    },
    {
      isLoading: true,
      isSignout: false,
      userToken: null,
      userType: "Nurse",
      data: {},
    }
  );

  React.useEffect(() => {
    // Fetch the token from storage then navigate to our appropriate place
    const bootstrapAsync = async () => {
      let userToken;

      try {
        // Restore token stored in `SecureStore` or any other encrypted storage
        // userToken = await SecureStore.getItemAsync('userToken');
      } catch (e) {
        // Restoring token failed
      }

      // After restoring token, we may need to validate it in production apps

      // This will switch to the App screen or Auth screen and this loading
      // screen will be unmounted and thrown away.
      dispatch({
        type: "RESTORE_TOKEN",
        token: userToken,
        userType: "Patient",
      });
    };

    bootstrapAsync();
  }, []);

  const authContext = React.useMemo(
    () => ({
      signIn: async (data) => {
        // In a production app, we need to send some data (usually username, password) to server and get a token
        // We will also need to handle errors if sign in failed
        // After getting token, we need to persist the token using `SecureStore` or any other encrypted storage
        // In the example, we'll use a dummy token
        console.log(data);

        let userType = "Patient";

        if (data.usernameOrEmail[0].toLowerCase() === "n") {
          userType = "Nurse";
        }

        dispatch({
          type: "SIGN_IN",
          token: "dummy-auth-token",
          userType: userType,
        });
      },
      signOut: () => dispatch({ type: "SIGN_OUT" }),
      signUp: async () => {
        // In a production app, we need to send user data to server and get a token
        // We will also need to handle errors if sign up failed
        // After getting token, we need to persist the token using `SecureStore` or any other encrypted storage
        // In the example, we'll use a dummy token

        dispatch({
          type: "SIGN_IN",
          token: "dummy-auth-token",
          userType: "Nurse",
        });
      },
    }),
    []
  );

  return (
    <NativeBaseProvider theme={theme}>
      <AuthContext.Provider value={{ ...authContext, state: state }}>
        <NavigationContainer>
          <Stack.Navigator>
            {state.isLoading ? (
              // We haven't finished checking for the token yet
              <Stack.Screen name="Splash" component={SplashScreen} />
            ) : state.userToken == null ? (
              // No token found, user isn't signed in
              <>
                <Stack.Screen
                  name="SignIn"
                  component={SignInScreen}
                  options={{
                    title: "Sign in",
                    // When logging out, a pop animation feels intuitive
                    animationTypeForReplace: state.isSignout ? "pop" : "push",
                  }}
                />
                <Stack.Screen
                  name="SignUp"
                  component={SignUpScreen}
                  options={{
                    title: "Sign up",
                    // When logging out, a pop animation feels intuitive
                    animationTypeForReplace: state.isSignout ? "pop" : "push",
                  }}
                />
              </>
            ) : state.userType === "Nurse" ? (
              // User is signed in as Nurse
              <>
                <Stack.Screen name="Home" component={NurseDashboard} />
                <Stack.Screen
                  name="Chemotherapies"
                  component={Chemotherapies}
                />
                <Stack.Screen
                  name="Extravasation Scale"
                  component={ExtravasationScale}
                />
                <Stack.Screen
                  name="Patient Clinical Pathway"
                  component={StartPatientPathway}
                />
                <Stack.Screen name="Follow Ups" component={NurseFollowUps} />
                <Stack.Screen
                  name="ChemotherapyInformation"
                  component={ChemotherapyInfoScreen}
                  options={({ route }) => ({
                    title: route.params.item.name,
                  })}
                />
                <Stack.Screen name="Follow Up" component={FollowUp} />
              </>
            ) : state.userType === "Patient" ? (
              //  User is signed in as Patient
              <>
                <Stack.Screen name="Home" component={PatientDashboard} />
                <Stack.Screen name="GeneralInformation" component={GeneralInformation} />
              </>
            ) : (
              <></>
            )}
          </Stack.Navigator>
        </NavigationContainer>
      </AuthContext.Provider>
    </NativeBaseProvider>
  );
}
