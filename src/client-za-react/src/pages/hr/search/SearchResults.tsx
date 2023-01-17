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
import { Data } from "../../../components/hr/search/SearchDatatypes";
import { ModuleDispatchContext } from "../../../contexts/ModuleContext";

const SearchResults: any = ({ record }: any) => {
  const navigate = useNavigate();
  const moduleDispatchContext = useContext(ModuleDispatchContext);

  return (
    <Box>
      <Typography fontSize={20} fontWeight={800} color="red" paddingLeft={2}>
        Total Results - {record.length}
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
          {record.map((hr: Data) => (
            <TableRow
              key={Math.random() * 1000}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              <TableCell>
                <Link
                  to={`/hr/Staff/${hr.staffId}`}
                  onClick={() => {
                    localStorage.setItem("staffId", String(hr.staffId));
                    moduleDispatchContext({
                      type: "toggle_tabsDisabled",
                      tabsDisabled: false,
                    });
                  }}
                >
                  {hr.employeeId}
                </Link>
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

export default SearchResults;
