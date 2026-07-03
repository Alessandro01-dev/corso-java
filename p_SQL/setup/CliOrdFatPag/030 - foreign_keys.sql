alter table pagamenti drop foreign key fk_pagamenti_fatture;
alter table fatture drop foreign key fk_fatture_ordini;
alter table ordini drop foreign key fk_ordini_clienti;

alter table pagamenti
add constraint fk_pagamenti_fatture
foreign key (id_fattura)
references fatture(id_fattura);

alter table fatture
add constraint fk_fatture_ordini
foreign key (id_ordine)
references ordini(id_ordine);

alter table ordini
add constraint fk_ordini_clienti
foreign key (id_cliente)
references clienti(id_cliente);

describe clienti;
describe ordini;
describe fatture;
describe pagamenti;