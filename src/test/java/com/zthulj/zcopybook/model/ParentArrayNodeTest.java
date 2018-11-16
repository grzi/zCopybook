package com.zthulj.zcopybook.model;

import org.junit.Assert;
import org.junit.Test;

public class ParentArrayNodeTest {

    @Test(expected = IllegalArgumentException.class)
    public void factory_occursNumber0_shouldThrowIllegalArg() {
        ParentNode root = Node.createRootNode();
        Node.createParentNodeArray(root, 0, 0);
    }

    @Test
    public void isParent_ParentArrayNode_shouldReturnFalse() {
        ParentNode root = Node.createRootNode();
        ParentArrayNode<Object> parent = Node.createParentNodeArray(root, 0, 1);

        Assert.assertEquals(true, parent.isParent());
    }

    @Test
    public void constructor_occurs3_shouldHave3ArrayInitialized() {
        ParentNode root = Node.createRootNode();
        ParentArrayNode<Object> parent = Node.createParentNodeArray(root, 0, 3);

        Assert.assertEquals(3, parent.getArray().length);
        Assert.assertNotNull(parent.getArray()[0]);
        Assert.assertNotNull(parent.getArray()[1]);
        Assert.assertNotNull(parent.getArray()[2]);
    }

    @Test
    public void addValueChild_occurs3_shouldAddValueInTheFirstArrayNode() {
        ParentNode root = Node.createRootNode();
        ParentArrayNode<Object> parent = Node.createParentNodeArray(root, 0, 3);

        ValueNode child = parent.addValueNode("TEST", Coordinates.from(0, 1));
        Assert.assertNotNull(child);
        Assert.assertEquals(child, parent.getArray()[0].getChilds().get("TEST"));
        Assert.assertEquals(0, parent.getArray()[1].getChilds().size());
        Assert.assertEquals(0, parent.getArray()[2].getChilds().size());

    }

    @Test
    public void addParentNode_occurs3_shouldAddTheParentInTheFirstArrayNode() {
        ParentNode root = Node.createRootNode();
        ParentArrayNode<Object> parent = Node.createParentNodeArray(root, 0, 3);

        ParentNode parent1 = parent.addParentNode("PARENT", 1);

        Assert.assertNotNull(parent1);
        Assert.assertEquals(parent1, parent.getArray()[0].getChilds().get("PARENT"));
        Assert.assertEquals(0, parent.getArray()[1].getChilds().size());
        Assert.assertEquals(0, parent.getArray()[2].getChilds().size());

    }

    @Test
    public void addParentArrayNode_occurs3_shouldAddTheParentArrayInTheFirstArrayNode() {
        ParentNode root = Node.createRootNode();
        ParentArrayNode<Object> parent = Node.createParentNodeArray(root, 0, 3);


        ParentArrayNode parent1 = parent.addParentArrayNode("PARENTARRAY", 1, 2);

        Assert.assertNotNull(parent1);
        Assert.assertEquals(parent1, parent.getArray()[0].getChilds().get("PARENTARRAY"));
        Assert.assertEquals(0, parent.getArray()[1].getChilds().size());
        Assert.assertEquals(0, parent.getArray()[2].getChilds().size());

    }


    @Test
    public void populateOccurs_occurs3OneChild_shouldPopulateOccursWithOKCoords() {
        ParentNode root = Node.createRootNode();
        ParentArrayNode<Object> parent = Node.createParentNodeArray(root, 0, 3);
        ValueNode child = parent.addValueNode("TEST", Coordinates.from(0, 1));

        parent.populateOccurs(2);

        Assert.assertEquals(child, parent.getArray()[0].getChilds().get("TEST"));
        ValueNode child2 = (ValueNode) parent.getArray()[1].getChilds().get("TEST");
        ValueNode child3 = (ValueNode) parent.getArray()[2].getChilds().get("TEST");

        Assert.assertNotNull(child2);
        Assert.assertNotNull(child3);
        Assert.assertEquals(Coordinates.from(2, 3), child2.getCoordinates());
        Assert.assertEquals(Coordinates.from(4, 5), child3.getCoordinates());
    }

    @Test
    public void populateOccurs_3occurs_AParentWithTwoChilds_shouldPopulateOccursWithOKCoords() {
        ParentNode root = Node.createRootNode();
        ParentArrayNode<Object> parent = Node.createParentNodeArray(root, 0, 3);

        ParentNode parent1 = parent.addParentNode("PARENT1", 1);
        parent1.addValueNode("VALUE1", Coordinates.from(0, 2));
        parent1.addValueNode("VALUE2", Coordinates.from(3, 5));

        parent.populateOccurs(6);

        ParentNode parent2 = (ParentNode) parent.getArray()[1].getChilds().get("PARENT1");
        ParentNode parent3 = (ParentNode) parent.getArray()[2].getChilds().get("PARENT1");


        Assert.assertNotNull(parent2);
        Assert.assertNotNull(parent3);

        Assert.assertEquals(Coordinates.from(6, 8), ((ValueNode) parent2.getChilds().get("VALUE1")).getCoordinates());
        Assert.assertEquals(Coordinates.from(9, 11), ((ValueNode) parent2.getChilds().get("VALUE2")).getCoordinates());

        Assert.assertEquals(Coordinates.from(12, 14), ((ValueNode) parent3.getChilds().get("VALUE1")).getCoordinates());
        Assert.assertEquals(Coordinates.from(15, 17), ((ValueNode) parent3.getChilds().get("VALUE2")).getCoordinates());
    }

    @Test
    public void populateOccurs_3occurs_AParentWithTwoParentChildWithTwoParentChildWithTwoChilds_shouldPopulateOccursWithOKCoords() {
        ParentNode root = Node.createRootNode();
        ParentArrayNode<Object> parent = Node.createParentNodeArray(root, 0, 3);

        ParentNode<Object> parent_1 = parent.addParentNode("PARENT1", 0);
        ParentNode<Object> parent_2 = parent.addParentNode("PARENT2", 0);

        ParentNode<Object> parent_1_1 = parent_1.addParentNode("PARENT1_1", 0);
        ParentNode<Object> parent_1_2 = parent_1.addParentNode("PARENT1_2", 0);
        ParentNode<Object> parent_2_1 = parent_2.addParentNode("PARENT2_1", 0);
        ParentNode<Object> parent_2_2 = parent_2.addParentNode("PARENT2_2", 0);

        parent_1_1.addValueNode("VALUE1", Coordinates.from(0, 2));
        parent_1_1.addValueNode("VALUE2", Coordinates.from(3, 5));
        parent_1_2.addValueNode("VALUE3", Coordinates.from(6, 8));
        parent_1_2.addValueNode("VALUE4", Coordinates.from(9, 11));
        parent_2_1.addValueNode("VALUE5", Coordinates.from(12, 14));
        parent_2_1.addValueNode("VALUE6", Coordinates.from(15, 18));
        parent_2_2.addValueNode("VALUE7", Coordinates.from(19, 29));
        parent_2_2.addValueNode("VALUE8", Coordinates.from(30, 42));

        parent.populateOccurs(43);

        // We'll get directly the 8 values of each occurs and test them

        ValueNode value_2_1_1_1 = (ValueNode) ((ParentNode) ((ParentNode) parent.getArray()[1].getChilds().get("PARENT1")).getChilds().get("PARENT1_1")).getChilds().get("VALUE1");
        ValueNode value_2_1_1_2 = (ValueNode) ((ParentNode) ((ParentNode) parent.getArray()[1].getChilds().get("PARENT1")).getChilds().get("PARENT1_1")).getChilds().get("VALUE2");
        ValueNode value_2_1_2_1 = (ValueNode) ((ParentNode) ((ParentNode) parent.getArray()[1].getChilds().get("PARENT1")).getChilds().get("PARENT1_2")).getChilds().get("VALUE3");
        ValueNode value_2_1_2_2 = (ValueNode) ((ParentNode) ((ParentNode) parent.getArray()[1].getChilds().get("PARENT1")).getChilds().get("PARENT1_2")).getChilds().get("VALUE4");
        ValueNode value_2_2_1_1 = (ValueNode) ((ParentNode) ((ParentNode) parent.getArray()[1].getChilds().get("PARENT2")).getChilds().get("PARENT2_1")).getChilds().get("VALUE5");
        ValueNode value_2_2_1_2 = (ValueNode) ((ParentNode) ((ParentNode) parent.getArray()[1].getChilds().get("PARENT2")).getChilds().get("PARENT2_1")).getChilds().get("VALUE6");
        ValueNode value_2_2_2_1 = (ValueNode) ((ParentNode) ((ParentNode) parent.getArray()[1].getChilds().get("PARENT2")).getChilds().get("PARENT2_2")).getChilds().get("VALUE7");
        ValueNode value_2_2_2_2 = (ValueNode) ((ParentNode) ((ParentNode) parent.getArray()[1].getChilds().get("PARENT2")).getChilds().get("PARENT2_2")).getChilds().get("VALUE8");

        ValueNode value_3_1_1_1 = (ValueNode) ((ParentNode) ((ParentNode) parent.getArray()[2].getChilds().get("PARENT1")).getChilds().get("PARENT1_1")).getChilds().get("VALUE1");
        ValueNode value_3_1_1_2 = (ValueNode) ((ParentNode) ((ParentNode) parent.getArray()[2].getChilds().get("PARENT1")).getChilds().get("PARENT1_1")).getChilds().get("VALUE2");
        ValueNode value_3_1_2_1 = (ValueNode) ((ParentNode) ((ParentNode) parent.getArray()[2].getChilds().get("PARENT1")).getChilds().get("PARENT1_2")).getChilds().get("VALUE3");
        ValueNode value_3_1_2_2 = (ValueNode) ((ParentNode) ((ParentNode) parent.getArray()[2].getChilds().get("PARENT1")).getChilds().get("PARENT1_2")).getChilds().get("VALUE4");
        ValueNode value_3_2_1_1 = (ValueNode) ((ParentNode) ((ParentNode) parent.getArray()[2].getChilds().get("PARENT2")).getChilds().get("PARENT2_1")).getChilds().get("VALUE5");
        ValueNode value_3_2_1_2 = (ValueNode) ((ParentNode) ((ParentNode) parent.getArray()[2].getChilds().get("PARENT2")).getChilds().get("PARENT2_1")).getChilds().get("VALUE6");
        ValueNode value_3_2_2_1 = (ValueNode) ((ParentNode) ((ParentNode) parent.getArray()[2].getChilds().get("PARENT2")).getChilds().get("PARENT2_2")).getChilds().get("VALUE7");
        ValueNode value_3_2_2_2 = (ValueNode) ((ParentNode) ((ParentNode) parent.getArray()[2].getChilds().get("PARENT2")).getChilds().get("PARENT2_2")).getChilds().get("VALUE8");

        Assert.assertEquals(Coordinates.from(43, 45), value_2_1_1_1.getCoordinates());
        Assert.assertEquals(Coordinates.from(46, 48), value_2_1_1_2.getCoordinates());
        Assert.assertEquals(Coordinates.from(49, 51), value_2_1_2_1.getCoordinates());
        Assert.assertEquals(Coordinates.from(52, 54), value_2_1_2_2.getCoordinates());
        Assert.assertEquals(Coordinates.from(55, 57), value_2_2_1_1.getCoordinates());
        Assert.assertEquals(Coordinates.from(58, 61), value_2_2_1_2.getCoordinates());
        Assert.assertEquals(Coordinates.from(62, 72), value_2_2_2_1.getCoordinates());
        Assert.assertEquals(Coordinates.from(73, 85), value_2_2_2_2.getCoordinates());

        Assert.assertEquals(Coordinates.from(86, 88), value_3_1_1_1.getCoordinates());
        Assert.assertEquals(Coordinates.from(89, 91), value_3_1_1_2.getCoordinates());
        Assert.assertEquals(Coordinates.from(92, 94), value_3_1_2_1.getCoordinates());
        Assert.assertEquals(Coordinates.from(95, 97), value_3_1_2_2.getCoordinates());
        Assert.assertEquals(Coordinates.from(98, 100), value_3_2_1_1.getCoordinates());
        Assert.assertEquals(Coordinates.from(101, 104), value_3_2_1_2.getCoordinates());
        Assert.assertEquals(Coordinates.from(105, 115), value_3_2_2_1.getCoordinates());
        Assert.assertEquals(Coordinates.from(116, 128), value_3_2_2_2.getCoordinates());
    }

    @Test
    public void populateOccurs_3occurs_ParentChildWithArrayParentChildWithAChildAnd2occurs_shouldPopulateOccursWithOKCoords() {
        ParentNode root = Node.createRootNode();
        ParentArrayNode<Object> parent = root.addParentArrayNode("ARRAY", 0, 3);
        ParentArrayNode<Object> parent2 = parent.addParentArrayNode("ARRAY_CHILD", 1,2);

        parent2.addValueNode("VALUE", Coordinates.from(0, 2));
        int nextStart = parent2.populateOccurs(3);
        parent.populateOccurs(nextStart);

        ValueNode value_1 = (ValueNode) ((ParentNode) ((ParentArrayNode) parent.getArray()[0].getChilds().get("ARRAY_CHILD")).getArray()[0]).getChilds().get("VALUE");
        ValueNode value_2 = (ValueNode) ((ParentNode) ((ParentArrayNode) parent.getArray()[0].getChilds().get("ARRAY_CHILD")).getArray()[1]).getChilds().get("VALUE");

        ValueNode value_3 = (ValueNode) ((ParentNode) ((ParentArrayNode) parent.getArray()[1].getChilds().get("ARRAY_CHILD")).getArray()[0]).getChilds().get("VALUE");
        ValueNode value_4 = (ValueNode) ((ParentNode) ((ParentArrayNode) parent.getArray()[1].getChilds().get("ARRAY_CHILD")).getArray()[1]).getChilds().get("VALUE");
        ValueNode value_5 = (ValueNode) ((ParentNode) ((ParentArrayNode) parent.getArray()[2].getChilds().get("ARRAY_CHILD")).getArray()[0]).getChilds().get("VALUE");
        ValueNode value_6 = (ValueNode) ((ParentNode) ((ParentArrayNode) parent.getArray()[2].getChilds().get("ARRAY_CHILD")).getArray()[1]).getChilds().get("VALUE");

        Assert.assertEquals(Coordinates.from(0, 2), value_1.getCoordinates());
        Assert.assertEquals(Coordinates.from(3, 5), value_2.getCoordinates());
        Assert.assertEquals(Coordinates.from(6, 8), value_3.getCoordinates());
        Assert.assertEquals(Coordinates.from(9, 11), value_4.getCoordinates());
        Assert.assertEquals(Coordinates.from(12, 14), value_5.getCoordinates());
        Assert.assertEquals(Coordinates.from(15, 17), value_6.getCoordinates());
    }

}
