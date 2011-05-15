package edu.ucsd.cse110.orgchart.visualizer;

import java.util.List;
import java.util.Map;

import edu.ucsd.cse110.orgchart.entry.OrgChartEntry;

public class OrgChartVisualizer {

	public static StringBuilder printEntryTree(
			Map<OrgChartEntry, List<OrgChartEntry>> tree, OrgChartEntry parent) {
		return printEntryTree(tree, parent, 0);
	}

	public static StringBuilder printEntryTree(
			Map<OrgChartEntry, List<OrgChartEntry>> tree, OrgChartEntry parent,
			int depth) {

		StringBuilder chart = new StringBuilder();
		List<OrgChartEntry> children = tree.get(parent);
		if (children != null) {
			StringBuilder indentation = new StringBuilder();
			for (int indent = 0; indent < depth * 4; indent++) {
				indentation.append(" ");
			}
			for (OrgChartEntry child : children) {
				StringBuilder line = new StringBuilder();
				line.append(" ").append(child.getName()).append(", ").append(
						child.getTitle()).append(" ");
				// top
				if (depth > 0) {
					for (int indent = 0; indent < (depth - 1) * 4; indent++) {
						chart.append(" ");
					}
					chart.append("|   ");
				}
				chart.append("/");
				for (int i = 0; i < line.length(); i++) {
					chart.append("-");
				}
				chart.append("\\\n");
				// middle
				if (depth > 0) {
					for (int i = 1; i < depth * 4 - 3; i++) {
						chart.append(" ");
					}
					chart.append("\\-->");
				}
				chart.append("|").append(line).append("|\n");
				// bottom
				chart.append(indentation).append("\\");
				for (int i = 0; i < line.length(); i++) {
					chart.append("-");
				}
				chart.append("/\n\n");
				chart.append(printEntryTree(tree, child, depth + 1));
			}
		}
		return chart;
	}
}
