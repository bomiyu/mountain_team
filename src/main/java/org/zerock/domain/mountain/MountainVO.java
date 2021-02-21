package org.zerock.domain.mountain;

import lombok.Data;

@Data
public class MountainVO {
   private Long no;
   private String mname;
   private String mloc;
   private int height;
   private int status;
   private String description;
   
}