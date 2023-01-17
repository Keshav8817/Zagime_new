// import AttachmentsContext from "../../../contexts/AttachmentsContext";
// import { TableCell, TableRow } from "@mui/material";
// import React, { useContext } from "react";
// import { Link } from "react-router-dom";
// import type { Data } from "../../../pages/hr/trainings/TrainingDatatypes";
// import type { FC } from "react";

// /**
//  * *HR* aka *Human Resources* module. \
//  * `TrainingsList` is used to list trainings of *HR* module.
//  * @param props
//  */
// const TrainingsList: FC<AppRecordListProps<Data>> = (props) => {
//   const context = useContext(AttachmentsContext);
//   return (
//     <>
//       {props.list.map((data, index) => (
//         <TableRow key={Math.random() * 1000}>
//           <TableCell sx={{ color: "black" }} align="center" size="small">
//             <Link to="view" onClick={() => context.setSelected(index)}>
//               Select
//             </Link>
//           </TableCell>
//           <TableCell sx={{ color: "black" }} align="center" size="small">
//             {data.dateOfTraining}
//           </TableCell>
//           <TableCell sx={{ color: "black" }} align="center" size="small">
//             {data.trainingName}
//           </TableCell>
//           <TableCell>{data.expiryDate}</TableCell>
//         </TableRow>
//       ))}
//     </>
//   );
// };

// export default TrainingsList;
import React from "react";

const TrainingsList = () => {
  return <div>TrainingsList</div>;
};

export default TrainingsList;
