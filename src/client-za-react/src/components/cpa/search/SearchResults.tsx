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
import type { Search } from "../../../pages/cpa/search/searchDatatypes";
import type { FC } from "react";
import { ModuleDispatchContext } from "../../../contexts/ModuleContext";
const SearchResults: FC<{
  list: Search[];
}> = (props) => {
  const navigate = useNavigate();
  const moduleDispatchContext = useContext(ModuleDispatchContext);
  const [data, setData] = useState<Search[]>(props.list);
  const handleSearchView = (id: any) => {};
  return (
    <Box>
      <Typography fontSize={20} fontWeight={800} color="red" paddingLeft={2}>
        Total Results - {props.list.length}
      </Typography>
      <Table sx={{ minWidth: 800 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>Reference ID</TableCell>
            <TableCell> Name</TableCell>
            <TableCell>Type</TableCell>
            <TableCell>Caseworker</TableCell>
            <TableCell>Status</TableCell>
          </TableRow>
        </TableHead>

        <TableBody>
          {props.list.map((cultural, index, list) => (
            <TableRow
              key={Math.random() * 1000}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              <TableCell>
                <Link
                  // to={`#`}
                  // onClick={() => {
                  //   console.log("id: ", cultural.culturalProgramId);
                  //   handleSearchView(cultural.culturalProgramId);
                  // }}
                  to={`/cpa/add_cpa/${cultural.culturalProgramId}`}
                  onClick={() => {
                    moduleDispatchContext({
                      type: "toggle_tabsDisabled",
                      tabsDisabled: false,
                    });
                  }}
                >
                  {cultural.referenceId}
                </Link>
              </TableCell>
              <TableCell>{cultural.name}</TableCell>
              <TableCell>{cultural.type}</TableCell>
              <TableCell>{cultural.caseworker}</TableCell>
              <TableCell>{cultural.status}</TableCell>
              <TableCell>
                <EditIcon
                  record={cultural}
                  list={list}
                  index={index}
                  setData={setData}
                />
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </Box>
  );
};

export default SearchResults;
