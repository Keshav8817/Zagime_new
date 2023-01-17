// import { useAppDispatch, useAppSelector } from "../../../library/hooks";
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
// import { Link, useNavigate } from "react-router-dom";
// import type { FhSearchResultQuery } from "../../../features/fh/search/slice";
// import type { FC } from "react";
// import { doGet as GetfileDetails } from "../../../features/familyHealing/fileDetails/slice";
// import { hideTabs, unhideTabs } from "../../../features/navBarSlice";
// import { doGet as getReferral } from "../../../features/familyHealing/referral/slice";
// import { doGet } from "../../../features/familyHealing/approval/slice";

// const SearchResults: FC = () => {
//   const dispatch = useAppDispatch();
//   const data = useAppSelector((state) => state.fhSearch.data);
//   const navigate = useNavigate();

//   const handleSelect = (id: number) => {
//     dispatch(GetfileDetails(id)).then(() => {
//       dispatch(getReferral(id));
//       dispatch(doGet(id));
//       dispatch(unhideTabs(true));
//       navigate(`/fh/fileDetails?fhFileDetailsId=${id}`);
//     });
//   };

//   return (
//     <Box>
//       <Typography fontSize={20} fontWeight={800} color="red" paddingLeft={2}>
//         Total Results - {data.length}
//       </Typography>
//       <Table sx={{ minWidth: 800 }}>
//         <TableHead>
//           <TableRow>
//             <TableCell>File No.</TableCell>
//             <TableCell>Client Name</TableCell>
//             <TableCell>Status</TableCell>
//             <TableCell>Caseworker</TableCell>
//             <TableCell>Start Date</TableCell>
//           </TableRow>
//         </TableHead>
//         <TableBody>
//           {data.map((fh: FhSearchResultQuery) => (
//             <TableRow
//               key={Math.random() * 1000}
//               sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
//             >
//               <TableCell>
//                 <Link
//                   to={"#"}
//                   onClick={() => {
//                     handleSelect(fh.fhFileDetailsId);
//                   }}
//                 >
//                   {fh.fileNo}
//                 </Link>
//               </TableCell>
//               <TableCell>{fh.clientName}</TableCell>
//               <TableCell>{fh.status}</TableCell>
//               <TableCell>{fh.caseworker}</TableCell>
//               <TableCell>{fh.startDate}</TableCell>
//             </TableRow>
//           ))}
//         </TableBody>
//       </Table>
//     </Box>
//   );
// };

// export default SearchResults;
// import EditIcon from "../EditIcon";

import {
  Box,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Typography,
} from "@mui/material";

import React, { useContext } from "react";

import { Link, useNavigate } from "react-router-dom";

import type { FC } from "react";
import { Data } from "../../../pages/familyHealing/search/SearchDatatype";
import { ModuleDispatchContext } from "../../../contexts/ModuleContext";

const SearchResults: any = ({ record }: any) => {
  const navigate = useNavigate();
  const moduleDispatchContext = useContext(ModuleDispatchContext);

  return (
    <Box>
      <Typography fontSize={20} fontWeight={800} color="red" paddingLeft={2}>
        Total Results - {record.length}
      </Typography>

      <Table sx={{ minWidth: 800 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>File Number</TableCell>

            <TableCell>Client Name</TableCell>

            <TableCell>Caseworker</TableCell>

            <TableCell>Start Date</TableCell>

            <TableCell>Status</TableCell>
          </TableRow>
        </TableHead>

        <TableBody>
          {record.map((familyHealing: any) => (
            <TableRow
              key={Math.random() * 1000}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              <TableCell>
                <Link
                  to={`/fh/fileDetails/${familyHealing.fhFileDetailsId}`}
                  onClick={() => {
                    localStorage.setItem(
                      "fhFiledetailsId",

                      String(familyHealing.fhFileDetailsId)
                    );
                    moduleDispatchContext({
                      type: "toggle_tabsDisabled",
                      tabsDisabled: false,
                    });
                  }}
                >
                  {familyHealing.fileNo}
                </Link>
              </TableCell>

              <TableCell>{familyHealing.clientName}</TableCell>

              <TableCell>{familyHealing.caseworker}</TableCell>

              <TableCell>{familyHealing.startDate}</TableCell>

              <TableCell>{familyHealing.status}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </Box>
  );
};

export default SearchResults;
