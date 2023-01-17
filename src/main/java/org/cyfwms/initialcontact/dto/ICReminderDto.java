package org.cyfwms.initialcontact.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.cyfwms.common.dto.ReminderDto;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ICReminderDto {

        @Getter
        @Setter
        private Long icReminderId;

        @Getter
        @Setter
        private Long fileDetailsId;

        @Getter
        @Setter
        private ReminderDto reminderDto;

        @Getter
        @Setter
        private Long fileNumber;

    }

