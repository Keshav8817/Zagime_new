// import { useAppDispatch, useAppSelector } from "../library/hooks";
import {
  Backdrop,
  Box,
  Modal,
  Fade,
  Typography,
  useMediaQuery,
  useTheme,
  // Link as MUILink,
  // Button,
} from "@mui/material";
import {
  // Link as ReactRouterLink,
  // Navigate,
  useNavigate,
} from "react-router-dom";
import React, { useState } from "react";
import type { FormEvent, ReactElement } from "react";
// import { doPost } from "../features/login/slice";
import "../index.css";

import Input from "../components/Input";
import axiosInstance from "../library/axiosInstance";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  border: "5px solid orange",
  boxShadow: 24,
  p: 4,
};

/**
 * The Login functional component.
 * @returns Login component skeleton.
 */
const Login = (): ReactElement => {
  const navigate = useNavigate();
  const theme = useTheme();

  const isMatch = useMediaQuery(theme.breakpoints.down("sm"));

  // const state = useAppSelector((state) => state.login);
  // const dispatch = useAppDispatch();

  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const [type, setType] = useState("password");
  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    const data: any = e.currentTarget;
    axiosInstance
      .post("/login/authenticate", {
        username: data.username.value,
        password: data.password.value,
      })
      .then((response) => {
        localStorage.setItem("jwtToken", response.data.jwtToken);
        navigate("/home");
      })
      .catch((err) => {
        alert(err);
      });
  };

  const ErrorDialog: ReactElement = (
    <Modal
      aria-labelledby="transition-modal-title"
      aria-describedby="transition-modal-description"
      open={open}
      onClose={handleClose}
      closeAfterTransition
      BackdropComponent={Backdrop}
      BackdropProps={{
        timeout: 500,
      }}
    >
      <Fade in={open}>
        <Box sx={style}>
          <Typography
            id="transition-modal-title"
            variant="h6"
            component="h2"
            sx={{ color: "orange" }}
          >
            Invalid Credentials
          </Typography>
          <Typography id="transition-modal-description" sx={{ mt: 2 }}>
            Username/Password combination does not match!
          </Typography>
        </Box>
      </Fade>
    </Modal>
  );

  return (
    <>
      {isMatch ? (
        <>
          <div className="flex justify-center ">
            <div className="justify-self-center">
              <img
                src="/img/zagime.jpg"
                alt="img"
                className="md:max-w-full h-32"
              />
            </div>
          </div>
          <form className="justify-self-center" onSubmit={submitHandler}>
            <div className="flex flex-row w-full">
              {/* <div className="flex flex-row w-1/2">
                {" "}
                <img
                  src="https://gigastore.blob.core.windows.net/images/loginimg.jpg "
                  alt="img"
                  className="w-full"
                />
              </div> */}
              <div className="flex bg-[#bae6fd] flex-col md:w-1/2 lg:w-1/2">
                <div className="justify-self-center flex flex-col gap-4 ">
                  {" "}
                  <h3 className="text-center text-4xl font-bold mt-2 pl-5 pr-5">
                    Zagimē Anishinabēk
                  </h3>
                  <h3 className="  text-center text-4xl font-bold pl-5 pr-5">
                    Child & Family Wellness System
                  </h3>
                  <div className="flex flex-col justify-center gap-10 p-8">
                    <Input
                      formLabelFlex=" 1 0"
                      id="username"
                      name="username"
                      value="Username"
                      required
                    />
                    <Input
                      formLabelFlex=" 1 0"
                      id="password"
                      name="passWord"
                      validationTitle="Password must be at least 6 characters long!"
                      validationPattern="^.{6,}$"
                      value="Password"
                      required
                      type="password"
                    />
                  </div>
                </div>
              </div>
            </div>
            <div className="flex flex-row w-full ">
              <div className="flex flex-row w-1/2"></div>

              <div className="flex flex-col items-center w-full  bg-white  mt-4">
                <button
                  className="shadow justify-center mr-20 bg-yellow-500 hover:bg.yellow-300 focus:shadow-outline focus:outline-none text-black font-bold py-2 px-10 rounded-lg"
                  type="submit"
                >
                  LOGIN
                </button>
                <div className="flex  mr-20 justify-center  mb-6 bg-white">
                  <label className="block text-blue-500 font-bold">
                    <span className="text-sm">Forgot Password</span>
                  </label>
                </div>
              </div>
              {ErrorDialog}
            </div>
          </form>
        </>
      ) : (
        <>
          <div className="flex justify-center ">
            <div className="justify-self-center">
              <img
                src="/img/zagime.jpg"
                alt="img"
                className="md:max-w-full h-32"
              />
            </div>
          </div>
          <form className="justify-self-center" onSubmit={submitHandler}>
            <div className="flex flex-row w-full">
              <div className="flex flex-row w-1/2">
                {" "}
                <img
                  src="https://gigastore.blob.core.windows.net/images/loginimg.jpg "
                  alt="img"
                  className="w-full"
                />
              </div>
              <div className="flex bg-[#bae6fd] flex-col md:w-1/2 lg:w-1/2">
                <div className="justify-self-center flex flex-col gap-4 ">
                  {" "}
                  <h3 className="text-center text-4xl font-bold mt-2">
                    Zagimē Anishinabēk
                  </h3>
                  <h3 className="  text-center text-4xl font-bold">
                    Child & Family Wellness System
                  </h3>
                  <div className="flex flex-col justify-center gap-10 p-8">
                    <Input
                      formLabelFlex="0.53 1 0"
                      id="username"
                      name="username"
                      value="Username"
                      required
                    />
                    <Input
                      formLabelFlex="0.53 1 0"
                      id="password"
                      name="passWord"
                      validationTitle="Password must be at least 6 characters long!"
                      validationPattern="^.{6,}$"
                      value="Password"
                      required
                      type="password"
                    />
                  </div>
                </div>
              </div>
            </div>
            <div className="flex flex-row w-full ">
              <div className="flex flex-row w-1/2"></div>

              <div className="flex flex-col items-center w-full  bg-white  mt-4">
                <button
                  className="shadow justify-center ml-36 bg-yellow-500 hover:bg.yellow-300 focus:shadow-outline focus:outline-none text-black font-bold py-2 px-10 rounded-lg"
                  type="submit"
                >
                  LOGIN
                </button>
                <div className="flex  ml-36 justify-center  mb-6 bg-white">
                  <label className="block text-blue-500 font-bold">
                    <span className="text-sm">Forgot Password</span>
                  </label>
                </div>
              </div>
              {ErrorDialog}
            </div>
          </form>
        </>
      )}
    </>
  );
};

export default Login;
