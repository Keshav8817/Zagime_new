export interface Attachment {
  participantId: number;
  participantAttachmentId: number;
  participantImageName: string;
  name: string;
  type: string;
  image?: string;
  attachmentType?: string;
}
