package com.psbc.springcloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Locale;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Dept implements Serializable {
    private Locale deptno;
    private String dname;
    private String db_source;
}
