package net.canarymod.plugin;

import java.util.ArrayList;

class CanaryPluginDependencyNode {
	
	private String name;
	public ArrayList<CanaryPluginDependencyNode> edges;
	
	CanaryPluginDependencyNode(String name) {
		this.name = name;
		this.edges = new ArrayList<CanaryPluginDependencyNode>();
	}
	
	String getName() {
		return this.name;
	}
	
	void addEdge(CanaryPluginDependencyNode node) {
		this.edges.add(node);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<"+this.name+">(");
		for(CanaryPluginDependencyNode node : this.edges) {
			sb.append(node.toString());
			sb.append(",");
		}
		int idx = sb.lastIndexOf(",");
		if(idx != -1)
			sb.deleteCharAt(idx);
		sb.append(")");
		
		return sb.toString();
	}
}
