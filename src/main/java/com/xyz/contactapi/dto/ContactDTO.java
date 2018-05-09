package com.xyz.contactapi.dto;
import lombok.*;

@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {
    public long id;
    public String name;
}
