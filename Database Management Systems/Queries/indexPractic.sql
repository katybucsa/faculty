create index index_NrMembrii_Echipaje on EchipajePolitie(NrMembrii)
select* from EchipajePolitie
order by NrMembrii


create index index_TipControl_Echipaje on EchipajePolitie(TipControl)


create index index_DataNastere_Soferi on Soferi(DataNastere)

create index index_Pid_Masini on Masini(Pid)
create index index_NrInmat_Masini on Masini(NrInmatriculare)
drop index index_Pid_Masini on Masini
select * from Masini
order by NrInmatriculare desc

