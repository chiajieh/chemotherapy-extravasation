import {
  Box,
  Button,
  Center,
  FormControl,
  Heading,
  Input,
  VStack,
} from "native-base";
import React from "react";
import { AuthContext } from "../components/AuthContext";

function SignUpScreen() {
  const [email, setEmail] = React.useState("");
  const [password, setPassword] = React.useState("");
  const [passwordConfirmation, setPasswordConfirmation] = React.useState("");
  const [errors, setErrors] = React.useState({});

  const { signUp } = React.useContext(AuthContext);

  const validate = () => {
    const currentErrors = {};

    if (email === "") {
      currentErrors.email = "Email is required";
    }

    if (password === "") {
      currentErrors.password = "Password is required";
    } else if (password !== passwordConfirmation) {
      currentErrors.password = "Password must match";
    }

    setErrors(currentErrors);

    for (var i in currentErrors) return false;
    return true;
  };

  const onSubmit = () => {
    validate() ? signUp({ email, password }) : console.log("Validation Failed");
  };

  return (
    <Center w="100%">
      <Box safeArea p="2" w="90%" maxW="290" py="8">
        <Heading
          size="lg"
          color="coolGray.800"
          _dark={{
            color: "warmGray.50",
          }}
          fontWeight="semibold"
        >
          Welcome
        </Heading>
        <Heading
          mt="1"
          color="coolGray.600"
          _dark={{
            color: "warmGray.200",
          }}
          fontWeight="medium"
          size="xs"
        >
          Sign up to continue!
        </Heading>
        <VStack space={3} mt="5">
          <FormControl isInvalid={"email" in errors}>
            <FormControl.Label>Email</FormControl.Label>
            <Input placeholder="Email" value={email} onChangeText={setEmail} />
            {"email" in errors && (
              <FormControl.ErrorMessage>
                {errors.email}
              </FormControl.ErrorMessage>
            )}
          </FormControl>
          <FormControl isInvalid={"password" in errors}>
            <FormControl.Label>Password</FormControl.Label>
            <Input
              placeholder="Password"
              value={password}
              onChangeText={setPassword}
              type="password"
            />
            {"password" in errors && (
              <FormControl.ErrorMessage>
                {errors.password}
              </FormControl.ErrorMessage>
            )}
          </FormControl>
          <FormControl isInvalid={"password" in errors}>
            <FormControl.Label>Confirm Password</FormControl.Label>
            <Input
              placeholder="Confirm Password"
              value={passwordConfirmation}
              onChangeText={setPasswordConfirmation}
              type="password"
            />
            {"password" in errors && (
              <FormControl.ErrorMessage>
                {errors.password}
              </FormControl.ErrorMessage>
            )}
          </FormControl>
          <Button onPress={() => onSubmit()} mt="2" colorScheme="cyan">
            Sign up
          </Button>
        </VStack>
      </Box>
    </Center>
  );
}

export default SignUpScreen;
