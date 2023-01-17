import AttachmentList from "../../../components/initialContact/attachments/AttachmentList";
import IcLayout from "../../../components/initialContact/ICLayout";
// import { doGet } from "../../../features/initialContact/attachments/slice";
// import { useAppDispatch, useAppSelector } from "../../../library/hooks";
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
import { Attachment } from "./AttachmentDataTypes";
import { searchICAttachments } from "./service";
import axiosInstance from "../../../library/axiosInstance";

/**
 * `IC` aka `Initial Contact` module.
 * Sub page: `Attachments`.
 * @returns `ReactElement`
 */
const Attachments: FC = () => {
  const { id } = useParams();
  const [attachmentsList, setAttachmentsList] = useState<Attachment[]>([]);
  //   {
  //     fileDetailsId: Number(id),
  //     icAttachmentId: 0,
  //     icAttachmentName: "",
  //     name: "",
  //     type: "",
  //   },
  // ]);
  // const [state, setState] = useState<Data>({
  //   fileDetailsId: Number(id),
  //   icAttachmentId: 0,
  //   icAttachmentName: "",
  //   name: "",
  //   type: "",
  // });
  useEffect(() => {
    axiosInstance
      .get(`initialcontactservice/attachments/read_all/${Number(id)}`, {
        headers: {
          authorization: `Bearer ${localStorage.getItem("jwtToken")}`,
        },
      })
      .then((response) => {
        setAttachmentsList(response.data);
      });
    // searchICAttachments(Number(id)).then(({ data }) => {
    //   setData(data);
    // });
  }, []);
  return (
    <IcLayout>
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
            <TableContainer>
              <Table sx={{ minWidth: 760 }} aria-label="simple table">
                <TableHead>
                  <TableRow sx={{ backgroundColor: "#FFBF00", color: "white" }}>
                    <TableCell align="center" size="small"></TableCell>
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
            </TableContainer>
          </Box>
        </Box>
      </Box>
    </IcLayout>
  );
};

export default Attachments;
