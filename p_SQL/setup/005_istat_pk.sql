-- definizione delle Primary Keys

alter table ripartizioni add primary key (cod_rip);

-- error chiave duplicata insert into ripartizioni (cod_rip, den_rip, cod_nuts1_2024) values (3, 'xxx', 'ggg');

describe ripartizioni;

alter table regioni add primary key (cod_reg);
describe regioni;

alter table province add primary key (cod_uts);
describe province;

alter table comuni add primary key (pro_com);
describe comuni;