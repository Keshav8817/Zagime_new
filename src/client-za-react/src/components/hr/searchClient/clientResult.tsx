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
import { Data } from "../search/SearchDatatypes";
import { ModuleDispatchContext } from "../../../contexts/ModuleContext";
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
      <Table sx={{ minWidth: 800 }}>
        <TableHead>
          <TableRow>
            <TableCell>Employee ID</TableCell>
            <TableCell>First Name</TableCell>
            <TableCell>Last Name</TableCell>
            <TableCell>Work Location</TableCell>
            <TableCell>Active</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {data.map((hr: Data) => (
            <TableRow
              key={Math.random() * 1000}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              <TableCell
                onClick={() => {
                  setClientName(hr.firstName + " " + hr.lastName);
                  setClientId(hr.staffId);
                  setClick(false);
                }}
              >
                {hr.employeeId}
              </TableCell>
              <TableCell>{hr.firstName}</TableCell>
              <TableCell>{hr.lastName}</TableCell>
              <TableCell>{hr.workLocation}</TableCell>
              <TableCell>{hr.active}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </Box>
  );
};

export default ClientResults;
