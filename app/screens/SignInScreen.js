import {
  Box,
  Button,
  Center,
  FormControl,
  Heading,
  HStack,
  Input,
  Link,
  Text,
  VStack,
} from "native-base";
import React from "react";
import { AuthContext } from "../components/AuthContext";

function SignInScreen({ navigation }) {
  const [email, setEmail] = React.useState("");
  const [password, setPassword] = React.useState("");
  const [errors, setErrors] = React.useState({});

  const { signIn } = React.useContext(AuthContext);

  const validate = () => {
    const currentErrors = {};

    if (email === "") {
      currentErrors.email = "Email is required";
    }

    if (password === "") {
      currentErrors.password = "Password is required";
    }

    setErrors(currentErrors);

    for (var i in currentErrors) return false;
    return true;
  };

  const onSubmit = () => {
    validate()
      ? signIn({ usernameOrEmail: email, password: password })
      : console.log("Validation Failed");
  };
  return (
    <Center w="100%">
      <Box safeArea p="2" py="8" w="90%" maxW="290">
        <Heading
          size="lg"
          fontWeight="600"
          color="coolGray.800"
          _dark={{
            color: "warmGray.50",
          }}
        >
          Welcome
        </Heading>
        <Heading
          mt="1"
          _dark={{
            color: "warmGray.200",
          }}
          color="coolGray.600"
          fontWeight="medium"
          size="xs"
        >
          Sign in to continue!
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
            <Link
              _text={{
                fontSize: "xs",
                fontWeight: "500",
                color: "indigo.500",
              }}
              alignSelf="flex-end"
              mt="1"
            >
              Forget Password?
            </Link>
          </FormControl>
          <Button onPress={() => onSubmit()} mt="2" colorScheme="cyan">
            Sign in
          </Button>
          <HStack mt="6" justifyContent="center">
            <Text
              fontSize="sm"
              color="coolGray.600"
              _dark={{
                color: "warmGray.200",
              }}
            >
              {"I'm a new user. "}
            </Text>
            <Link
              _text={{
                color: "indigo.500",
                fontWeight: "medium",
                fontSize: "sm",
              }}
              onPress={() => navigation.navigate("SignUp")}
            >
              Sign Up
            </Link>
          </HStack>
        </VStack>
      </Box>
    </Center>
  );
}

export default SignInScreen;
