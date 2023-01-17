import { ModuleDispatchContext } from "../../../contexts/ModuleContext";
import EditIcon from "./EditIcon";
import {
  Box,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Typography,
} from "@mui/material";
import React, { useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import type { Search } from "../../../pages/cyfms/search/searchDatatypes";
import type { FC } from "react";

const SearchResults: FC<{
  list: Search[];
}> = (props) => {
  const navigate = useNavigate();
  const moduleDispatchContext = useContext(ModuleDispatchContext);
  const [data, setData] = useState<Search[]>(props.list);
  const handleSearchView = (id: any) => {
    // dispatch(doGetCounselors(id)).then(() => {
    //   navigate("/cyfms/register");
    // });
  };

  return (
    <Box>
      <Typography fontSize={20} fontWeight={800} color="red" paddingLeft={2}>
        Total Results - {props.list.length}
      </Typography>
      <Table sx={{ minWidth: 800 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>Reference ID</TableCell>
            <TableCell>First Name</TableCell>
            <TableCell>Middle Name</TableCell>
            <TableCell>Last Name</TableCell>
            <TableCell>Date of Birth</TableCell>
            <TableCell>City</TableCell>
            <TableCell>Phone Number</TableCell>
            {/* <TableCell>Action</TableCell> */}
          </TableRow>
        </TableHead>
        <TableBody>
          {props.list.map((participant, index, list) => (
            <TableRow
              key={Math.random() * 1000}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              <TableCell>
                <Link
                  to={`/cyfms/register/${participant.participantId}`}
                  onClick={() => {
                    moduleDispatchContext({
                      type: "change_id",
                      id: participant.participantId,
                    });
                    moduleDispatchContext({
                      type: "toggle_tabsDisabled",
                      tabsDisabled: false,
                    });
                    moduleDispatchContext({
                      type: "toggle_editMode",
                      editMode: true,
                    });
                  }}
                >
                  {participant.referenceId}
                </Link>
              </TableCell>
              <TableCell>{participant.firstname}</TableCell>
              <TableCell>{participant.middleName}</TableCell>
              <TableCell>{participant.surname}</TableCell>
              <TableCell>{participant.dateOfBirth}</TableCell>
              <TableCell>{participant.city}</TableCell>
              <TableCell>{participant.workPhone}</TableCell>
              {/* <TableCell>
                <EditIcon
                  record={participant}
                  list={list}
                  index={index}
                  setData={setData}
                />
              </TableCell> */}
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </Box>
  );
};

export default SearchResults;
