package com.zte.chy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MaterialPage extends PageMod {

	private String materialcode;

	private String startTime;

	private String endTime;

	private String barcode;

}
