package com.securityjwt.customException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException {
		private Integer errorId;
		private String errorName;
		private String traceId;
}
