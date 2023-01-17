import AttachmentsContext from "../../../contexts/AttachmentsContext";
import { TableCell, TableRow } from "@mui/material";
import React, { useContext } from "react";
import { Link, useParams } from "react-router-dom";
// import type { Record as RecordT } from "../../../features/initialContact/attachments/slice";
import type { FC } from "react";

/**
 * `AttachmentList` is used to list attachments of `IC` aka \
 * `Initial Contact` module.
 * @param props
 * @returns `ReactElement`
 */
const AttachmentList: FC<AppRecordListProps<any>> = (props) => {
  const context = useContext(AttachmentsContext);
  return (
    <>
      {props.list.map((attachment, index) => (
        <TableRow key={Math.random() * 1000}>
          <TableCell sx={{ color: "black" }} align="center" size="small">
            <Link
              to={`../attachments/view/${attachment.fileDetailsId}/${attachment.icAttachmentId}`}
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
