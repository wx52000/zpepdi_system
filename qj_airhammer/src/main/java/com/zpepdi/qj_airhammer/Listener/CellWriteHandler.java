package com.zpepdi.qj_airhammer.Listener;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

public class CellWriteHandler  extends AbstractColumnWidthStyleStrategy {
//    @Override
//    protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<WriteCellData<?>> list, Cell cell, Head head, Integer integer, Boolean aBoolean) {
//        // 简单设置
//        Sheet sheet = writeSheetHolder.getSheet();
//        sheet.setColumnWidth(cell.getColumnIndex(), 3000);
//    }
}
