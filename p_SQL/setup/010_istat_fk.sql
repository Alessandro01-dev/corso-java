-- definizione foreign keys
alter table comuni drop constraint fk_comuni_ripartizioni;
alter table comuni drop constraint fk_comuni_regioni;
alter table comuni drop constraint fk_comuni_province;
alter table province drop constraint fk_province_regioni;
alter table regioni drop constraint fk_regioni_ripartizioni;


alter table regioni -- (tabella che dipende da / tabella figlia)
add constraint fk_regioni_ripartizioni
foreign key (cod_rip)
references ripartizioni(cod_rip);

-- province ? quali FK?
alter table province
add constraint fk_province_regioni
foreign key (cod_reg)
references regioni(cod_reg);


alter table comuni
add constraint fk_comuni_province
foreign key (cod_uts)
references province(cod_uts);

alter table comuni 
add constraint fk_comuni_ripartizioni
foreign key (cod_rip)
references ripartizioni(cod_rip);

alter table comuni
add constraint fk_comuni_regioni
foreign key (cod_reg)
references regioni(cod_reg);

describe ripartizioni;
describe regioni;
describe province;
describe comuni;
