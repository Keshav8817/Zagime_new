import HrLayout from "../../../components/hr/HrLayout";
import AttachmentList from "../../../components/hr/attachments/AttachmentList";

import {
  Box,
  Button,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";
import { grey } from "@mui/material/colors";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import type { FC } from "react";
import { searchHrAttachments } from "./service";
import { Attachment } from "./AttachmentsDatatypes";
import axiosInstance from "../../../library/axiosInstance";

/**
 * `HR` aka `Human Resources` module.
 * Sub page: `Attachments`.
 */
const Attachments: FC = () => {
  const { id } = useParams();
  const [attachmentsList, setAttachmentsList] = useState<Attachment[]>([]);
  //   {
  //     staffId: Number(id),
  //     staffAttachmentId: 0,
  //     staffAttachmentName: "",
  //     name: "",
  //     type: "",
  //   },
  // ]);
  // const [state, setState] = useState<Data>({
  //   staffId: Number(id),
  //   staffAttachmentId: 0,
  //   staffAttachmentName: "",
  //   name: "",
  //   type: "",
  // });
  useEffect(() => {
    axiosInstance
      .get(`staffservice/attachments/read_all/${Number(id)}`, {
        headers: {
          authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
        },
      })
      .then((response) => {
        setAttachmentsList(response.data);
      });
    // searchHrAttachments(Number(id)).then(({ data }) => {
    //   setData(data);
    // });
  }, []);
  return (
    <HrLayout>
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: "1rem 0",
        }}
      >
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box
            sx={{
              flexBasis: 0,
              flexGrow: 2,
              display: "flex",
              justifyContent: "flex-end",
            }}
          >
            <Button
              sx={{ backgroundColor: "#ffbf00" }}
              component={Link}
              to={`../attachments/add/${id}`}
              variant="contained"
            >
              Add New
            </Button>
          </Box>
        </Box>
        <Box sx={{ display: "flex", flexWrap: "wrap", gap: "0 1rem" }}>
          <Box sx={{ flexBasis: 0, flexGrow: 1 }}>
            <Table sx={{ minWidth: 760 }} aria-label="simple table">
              <TableHead>
                <TableRow sx={{ backgroundColor: "#FFBF00", color: "white" }}>
                  <TableCell
                    sx={{ color: "white" }}
                    align="center"
                    size="small"
                  ></TableCell>
                  <TableCell align="center" size="small">
                    Document
                  </TableCell>
                  <TableCell align="center" size="small">
                    Type
                  </TableCell>
                </TableRow>
              </TableHead>
              <TableBody
                sx={{
                  "& > tr > td": {
                    backgroundColor: grey["400"],
                    p: "0.25rem",
                  },
                  "& > tr": { border: 0 },
                }}
              >
                <AttachmentList list={attachmentsList} />
              </TableBody>
            </Table>
          </Box>
        </Box>
      </Box>
    </HrLayout>
  );
};

export default Attachments;
