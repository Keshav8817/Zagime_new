// import { setView } from "../../features/popupSlice";
// import {
//   doGet as doGetculturalProgramActivity,
//   editState,
// } from "../../features/cpa/culturalProgramActivity/slice";
// import {
//   doGet as CPAGetParticipant,
//   doSearch,
// } from "../../features/cpa/participant/slice";
// import { doGet as CPAGetAttachments } from "../../features/cpa/attachments/slice";
// import { useAppDispatch, useAppSelector } from "../../library/hooks";
// import EditIcon from "./EditIcon";
// import {
//   Box,
//   Table,
//   TableBody,
//   TableCell,
//   TableHead,
//   TableRow,
//   Typography,
// } from "@mui/material";
// import React from "react";
// import { Record } from "../../features/cpa/search/slice";
// import { Link, Navigate, useNavigate } from "react-router-dom";
// import type { FC } from "react";
// import type { ReactElement } from "react";
// import { unhideTabs } from "../../features/navBarSlice";

// const CPASearchResult: FC = () => {
//   const dispatch = useAppDispatch();
//   const data = useAppSelector((state) => state.cpaSearch.data);
//   const navigate = useNavigate();
//   const handleSearchView = (id: any) => {
//     dispatch(setView(true));
//     // dispatch(setEdit(true));
//     dispatch(editState(true));
//     // dispatch(doGetculturalProgramActivity(id));
//     dispatch(doGetculturalProgramActivity(id)).then((res: any) => {
//       // dispatch(doSearch(id))
//       //   .unwrap()

//       //   .catch((err) => {});
//       dispatch(CPAGetParticipant(id));
//       dispatch(CPAGetAttachments(id));
//       navigate(`/cpa/add_cpa?culturalProgramId=${id}`);
//       dispatch(unhideTabs(true));
//     });
//   };
//   return (
//     <Box>
//       <Typography fontSize={20} fontWeight={800} color="red" paddingLeft={2}>
//         Total Results - {data.length}
//       </Typography>
//       <Table sx={{ minWidth: 800 }} aria-label="simple table">
//         <TableHead>
//           <TableRow>
//             <TableCell>Reference ID</TableCell>
//             <TableCell> Name</TableCell>
//             <TableCell>Type</TableCell>
//             <TableCell>Caseworker</TableCell>
//             <TableCell>Status</TableCell>

//             <TableCell>Action</TableCell>
//           </TableRow>
//         </TableHead>
//         <TableBody>
//           {data.map((participant: Record) => (
//             <TableRow
//               key={Math.random() * 1000}
//               sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
//             >
//               <TableCell>
//                 <Link
//                   to={`#`}
//                   onClick={() => {
//                     handleSearchView(participant.culturalProgramId);
//                   }}
//                 >
//                   {participant.referenceId}
//                 </Link>
//               </TableCell>

//               <TableCell>{participant.name}</TableCell>
//               <TableCell>{participant.type}</TableCell>
//               <TableCell>{participant.caseworker}</TableCell>
//               <TableCell>{participant.status}</TableCell>

//               <TableCell>
//                 <EditIcon value={participant.culturalProgramId} />
//               </TableCell>
//             </TableRow>
//           ))}
//         </TableBody>
//       </Table>
//     </Box>
//   );
// };

// export default CPASearchResult;
import React from "react";

function CPASearchResult() {
  return <div>CPASearchResult</div>;
}

export default CPASearchResult;
