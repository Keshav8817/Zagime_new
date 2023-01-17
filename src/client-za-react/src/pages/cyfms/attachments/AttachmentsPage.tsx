import AttachmentList from "../../../components/cyfms/attachments/AttachmentList";
import CyfmsLayout from "../../../components/cyfms/CYFMSLayout";
import axiosInstance from "../../../library/axiosInstance";
import {
  Box,
  Button,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
} from "@mui/material";
import { grey } from "@mui/material/colors";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import type { Attachment } from "./attachmentsDatatypes";
import type { FC } from "react";

/**
 * `CYFMS` aka `Child, Youth, and Family Management Services` module.
 * Sub page: `Attachments`.
 * @returns `ReactElement`
 */
const AttachmentsPage: FC = () => {
  const { id } = useParams();
  const [participantId, setParticipantId] = useState<number>(0);
  const [attachmentsList, setAttachmentsList] = useState<Attachment[]>([]);

  useEffect(() => {
    axiosInstance
      .get(`participantservice/attachments/read_all/${id}`, {
        headers: {
          authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
        },
      })
      .then((response) => {
        setAttachmentsList(response.data);
      });
  }, []);

  return (
    <CyfmsLayout>
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
              to={`/cyfms/attachments/add/${id}`}
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
    </CyfmsLayout>
  );
};

export default AttachmentsPage;
