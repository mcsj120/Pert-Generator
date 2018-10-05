import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class recursive {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		ArrayList<Node> aAncest = new ArrayList<Node>();
		ArrayList<Node> bAncest = new ArrayList<Node>();
		ArrayList<Node> cAncest = new ArrayList<Node>();
		ArrayList<Node> dAncest = new ArrayList<Node>();
		ArrayList<Node> eAncest = new ArrayList<Node>();
		ArrayList<Node> fAncest = new ArrayList<Node>();
		ArrayList<Node> allNodes = new ArrayList<Node>();
		
		Node A = new Node("a",5);
		
		Node B = new Node();
		B.setHead(false);
		B.setName("b");
		Node C = new Node();
		C.setHead(false);
		C.setName("c");
		Node D = new Node();
		D.setHead(false);
		D.setName("d");
		Node E = new Node();
		E.setHead(false);
		E.setName("e");
		Node F = new Node();
		F.setHead(false);
		F.setName("f");
		
		aAncest.add(B);
		aAncest.add(C);
		A.setAncestors(aAncest);
		
		bAncest.add(F);
		B.setAncestors(bAncest);
		
		cAncest.add(D);
		C.setAncestors(cAncest);
		
		dAncest.add(E);
		D.setAncestors(dAncest);
		
		E.setAncestors(eAncest);
		
		fAncest.add(E);
		F.setAncestors(fAncest);
		
		
		
		allNodes.add(A);
		allNodes.add(B);
		allNodes.add(C);
		allNodes.add(D);
		allNodes.add(E);
		allNodes.add(F);
		
		NodeList list = NodeList.getInstance();
		list.setNodeList(allNodes);
		Organizer thing = new Organizer(list);
		
		thing.recursiveStartPath();
		ArrayList<PathData> pathList = thing.getPathList();
		assertTrue(pathList.size()==2);
	}

}
