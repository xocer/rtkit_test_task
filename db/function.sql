create function get_info(in color_id varchar(2))
    returns varchar(50) as
$$
DECLARE
    names varchar(50) ;
begin
    select name INTO names from tsveta where color_number = color_id;
    return names;
end
$$
    language plpgsql;