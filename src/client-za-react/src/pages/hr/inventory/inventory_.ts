// // import {
// //   doGet,
// //   addMoreRecord,
// //   doPost,
// // } from "../../../features/cg/inventory/slice";
// // import type { Data, Record } from "../../../features/cg/inventory/slice";
// // import type { AppDispatch } from "../../../library/store";
// import type { NavigateFunction } from "react-router-dom";

// /**
//  * Callback of useEffect hook.
//  * @param dispatch - Redux action(s) dispatcher
//  * @param staffID - Staff ID
//  */
// // export const handleEffect = (dispatch: AppDispatch, staffID: number) => {
// //   dispatch(doGet(staffID))
// //     .unwrap()
// //     .catch((err) => {
// //       console.log(err);
// //     });
// // };

// /**
//  * Callback of "Add More" button.
//  * @param event - Mouse event object
//  * @param dispatch - Redux action(s) dispatcher
//  * @param form - Form node
//  * @param staffID - Staff ID
//  * @param data - Redux store data
//  */
// export const handleAddMore: AppMouseEventHandler<HTMLButtonElement> = (
//   event,
//   dispatch: AppDispatch,
//   form: HTMLFormElement,
//   staffID: number,
//   data: Data
// ) => {
//   event.preventDefault();
//   /** Are all the records removed on the UI? */
//   const flag: boolean = data.recordsList.length > 0;
//   dispatch(
//     addMoreRecord({
//       staffId: staffID,
//       staffInventoryId: flag
//         ? data.recordsList[data.recordsList.length - 1].staffInventoryId
//         : 0,
//       item: flag ? form[`record_${data.recordsList.length}_Item`].value : "",
//       serialNo: flag
//         ? form[`record_${data.recordsList.length}_SerialNumber`].value
//         : "",
//       from: flag ? form[`record_${data.recordsList.length}_From`].value : "",
//       to: flag ? form[`record_${data.recordsList.length}_To`].value : "",
//       inUse: flag ? form[`record_${data.recordsList.length}_InUse`].value : "",
//       notes: flag ? form[`record_${data.recordsList.length}_Notes`].value : "",
//     } as Record)
//   );
// };

// /**
//  * Callback of onSubmit event.
//  * @param event - Form event object
//  * @param navigate - React Router navigator
//  * @param dispatch - Redux action(s) dispatcher
//  * @param staffID - Staff ID
//  * @param data - Redux store data
//  */
// export const handleSubmit: AppFormEventHandler<HTMLFormElement> = (
//   event,
//   navigate: NavigateFunction,
//   dispatch: AppDispatch,
//   staffID: number,
//   data: Data
// ) => {
//   event.preventDefault();
//   const formData: Data = {
//     recordsList: new Array<Record>(data.recordsList.length),
//   };
//   for (let index = 0; index < data.recordsList.length; ++index) {
//     formData.recordsList[index] = {
//       staffId: staffID,
//       staffInventoryId: data.recordsList[index].staffInventoryId,
//       item: event.currentTarget[`record_${index + 1}_Item`].value,
//       serialNo: event.currentTarget[`record_${index + 1}_SerialNumber`].value,
//       from: event.currentTarget[`record_${index + 1}_From`].value,
//       to: event.currentTarget[`record_${index + 1}_To`].value,
//       inUse: event.currentTarget[`record_${index + 1}_InUse`].checked,
//       notes: event.currentTarget[`record_${index + 1}_Notes`].value,
//     };
//   }
//   dispatch(doPost(formData))
//     .unwrap()
//     .then(() => {
//       navigate("../staff");
//     })
//     .catch((err) => {
//       console.log(err);
//     });
// };
import React from "react";

const inventory_ = () => {
  return inventory_;
};

export default inventory_;
