import AttachmentsContext from "../../../contexts/AttachmentsContext";
import { TableCell, TableRow } from "@mui/material";
import React, { useContext } from "react";
import { Link } from "react-router-dom";
import type { Attachment } from "../../../pages/hr/attachments/AttachmentsDatatypes";
import type { FC } from "react";

/**
 * *HR* aka *Human Resources* module.
 * `AttachmentList` is used to list attachments of *HR* module.
 * @param props
 */
const AttachmentList: FC<AppRecordListProps<Attachment>> = (props) => {
  const context = useContext(AttachmentsContext);
  return (
    <>
      {props.list.map((attachment) => (
        <TableRow key={Math.random() * 1000}>
          <TableCell sx={{ color: "black" }} align="center" size="small">
            <Link
              to={`../attachments/view/${attachment.staffId}/${attachment.staffAttachmentId}`}
              onClick={() => context.setAttachment(attachment)}
            >
              Select
            </Link>
          </TableCell>
          <TableCell sx={{ color: "black" }} align="center" size="small">
            {attachment.name}
          </TableCell>
          <TableCell sx={{ color: "black" }} align="center" size="small">
            {attachment.type}
          </TableCell>
        </TableRow>
      ))}
    </>
  );
};

export default AttachmentList;
