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
import React from "react";
import type { ReactElement } from "react";

const ClientResults = ({
  setClick,
  moduleName,
  searchId,
  data,
  setClientName,
  setClientId,
}: any): ReactElement => {
  return (
    <Box>
      <Typography fontSize={20} fontWeight={800} color="red" paddingLeft={2}>
        Total Results - {data?.length}
      </Typography>
      <Table sx={{ minWidth: 800 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>Reference ID</TableCell>
            <TableCell>First Name</TableCell>
            <TableCell>Last Name</TableCell>
            <TableCell>City</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {data?.map((participant: any) => (
            <TableRow
              key={Math.random() * 1000}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              <TableCell
                onClick={() => {
                  setClientName(
                    participant.firstname + " " + participant.surname
                  );
                  setClientId(participant.participantId);
                  setClick(false);
                }}
              >
                {participant.referenceId}
              </TableCell>
              <TableCell>{participant.firstname}</TableCell>
              <TableCell>{participant.surname}</TableCell>
              <TableCell>{participant.city}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </Box>
  );
};

export default ClientResults;
// import React from "react";

// const ClientResults = () => {
//   return <div>ClientResults</div>;
// };

// export default ClientResults;
