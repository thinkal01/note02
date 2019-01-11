package com.note.common.freemarker.crud.dao;

import com.note.common.freemarker.crud.orm.Position;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PositionDao {

    private static Map<Integer, Position> positions = null;

    static {
        positions = new HashMap<Integer, Position>();

        positions.put(1, new Position(1, "架构师"));
        positions.put(2, new Position(2, "高级工程师"));
        positions.put(3, new Position(3, "中级工程师"));
        positions.put(4, new Position(4, "初级工程师"));
        positions.put(5, new Position(5, "java实习生"));
    }

    // 模拟查询所有数据
    public Collection<Position> queryAllPositions() {
        return positions.values();
    }

    // 模拟通过id查询数据
    public Position getPositionById(Integer id) {
        return positions.get(id);
    }

}
