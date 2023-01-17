import axios from "axios";
// import jwtDecode from "jwt-decode";
// import { setLoginFalse } from "../features/login/slice";
// import { store } from "../library/store";

const axiosInstance = axios.create({
  baseURL: `${
    process.env.REACT_APP_REST_API_TEST || "http://localhost:9088"
  }/v1/`,
});

axiosInstance.interceptors.request.use(
  function (config) {
    return config;
  },
  function (err) {
    return err;
  }
);

// axiosInstance.interceptors.response.use(
//   function (response) {
//     return response;
//   },
//   function (err) {
//     const data: any = store.getState();
//     const { dispatch } = store;
//     const token: any = jwtDecode(data.login.token);
//     if (token.exp < Date.now() / 1000) {
//       dispatch(setLoginFalse(false));
//     }
//   }
// );

export default axiosInstance;
