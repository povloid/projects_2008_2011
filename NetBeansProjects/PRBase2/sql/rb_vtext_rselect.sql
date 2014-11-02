with recursive tree (vtext, id, parent_id, level, pathstr, parent_rb_root_id, parent_rb_name, parent_rb_id)
as (select vtext, id, parent_id ,0, cast('' as text) , parent_rb_root_id, parent_rb_name, parent_rb_id
   from ips.rb_obj
   where parent_id is null AND rb_name='LOCATIONS'
union all
   select v.vtext, v.id, v.parent_id,tree.level + 1, tree.pathstr || '/' ||v.id, v.parent_rb_root_id, v.parent_rb_name, v.parent_rb_id
   from ips.rb_obj v
     inner join tree on tree.id = v.parent_id)
     
select id, parent_id,level, vtext, pathstr, parent_rb_root_id, parent_rb_name, parent_rb_id from tree  where level > 0  order by pathstr;
