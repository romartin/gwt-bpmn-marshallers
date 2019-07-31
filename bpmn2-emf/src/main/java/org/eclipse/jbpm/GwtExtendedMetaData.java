package org.eclipse.jbpm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;

/**
 * This class overrides some methods to make the code from parent class being exposed to the gwt compiler,
 * as the original code has some issues.
 */
public class GwtExtendedMetaData extends BasicExtendedMetaData {

    // TODO: Use the right pattern here, the original one was "\\w"
    public static final String WORD_SPLIT_REGEXP = ",";

    @Override
    // Just a copy of the parent's method but replacing the regexp to be used.
    protected List<EDataType> basicGetMemberTypes(EDataType eDataType) {
        EAnnotation eAnnotation = getAnnotation(eDataType, false);
        if (eAnnotation != null)
        {
            String memberTypes = eAnnotation.getDetails().get("memberTypes");
            if (memberTypes != null)
            {
                List<EDataType> result = new ArrayList<EDataType>();
                for (String member : memberTypes.split(WORD_SPLIT_REGEXP))
                {
                    int index = member.lastIndexOf("#");
                    EClassifier type =
                            index == -1 ?
                                    getType(eDataType.getEPackage(), member) :
                                    index == 0 ?
                                            getType((String)null, member.substring(1)) :
                                            getType(member.substring(0, index), member.substring(index + 1));
                    if (type instanceof EDataType)
                    {
                        result.add((EDataType)type);
                    }
                }
                return result;
            }
        }

        return Collections.emptyList();
    }

    @Override
    // Just a copy of the parent's method but replacing the regexp to be used.
    protected List<String> basicGetWildcards(EStructuralFeature eStructuralFeature) {
        EAnnotation eAnnotation = getAnnotation(eStructuralFeature, false);
        if (eAnnotation != null)
        {
            String wildcards = eAnnotation.getDetails().get("wildcards");
            if (wildcards != null)
            {
                List<String> result = new ArrayList<String>();
                for (String wildcard : wildcards.split(WORD_SPLIT_REGEXP))
                {
                    if (wildcard.equals("##other"))
                    {
                        result.add("!##" + getNamespace(eStructuralFeature.getEContainingClass().getEPackage()));
                    }
                    else if (wildcard.equals("##local"))
                    {
                        result.add(null);
                    }
                    else if (wildcard.equals("##targetNamespace"))
                    {
                        result.add(getNamespace(eStructuralFeature.getEContainingClass().getEPackage()));
                    }
                    else
                    {
                        result.add(wildcard);
                    }
                }
                return result;
            }
        }

        return Collections.emptyList();
    }

    @Override
    // Just a copy of the parent's method but replacing the regexp to be used.
    protected List<String> basicGetEnumerationFacet(EDataType eDataType) {
        EAnnotation eAnnotation = getAnnotation(eDataType, false);
        if (eAnnotation != null)
        {
            String enumerationLiteral = eAnnotation.getDetails().get("enumeration");
            if (enumerationLiteral != null)
            {
                List<String> result = new ArrayList<String>();
                for (String item : enumerationLiteral.split(WORD_SPLIT_REGEXP))
                {
                    String enumeration = replace(replace(item, "%20", " "), "%25", "%");
                    result.add(enumeration);
                }
                return result;
            }
        }
        return Collections.emptyList();
    }

    @Override
    // Just a copy of the parent's method but replacing the regexp to be used.
    protected List<String> basicGetPatternFacet(EDataType eDataType) {
        EAnnotation eAnnotation = getAnnotation(eDataType, false);
        if (eAnnotation != null)
        {
            String patternLiteral = eAnnotation.getDetails().get("pattern");
            if (patternLiteral != null)
            {
                List<String> result = new ArrayList<String>();
                for (String item : patternLiteral.split(WORD_SPLIT_REGEXP))
                {
                    String pattern = replace(replace(item, "%20", " "), "%25", "%");
                    result.add(pattern);
                }
                return result;
            }
        }
        return Collections.emptyList();
    }

    // Just a copy of the parent's replace method, as it's private
    private static String replace(String in, String oldString, String newString)
    {
        if (in == null || oldString == null)
        {
            return in;
        }

        int oldStringLength = oldString.length();
        if (oldStringLength == 0)
        {
            return in;
        }

        if (newString == null)
        {
            newString = "";
        }
        int newStringLength = newString.length();

        int index = -newStringLength;
        StringBuffer result = new StringBuffer(in);
        while((index = indexOf(result, oldString, index + newStringLength)) >= 0)
        {
            result.replace(index, index + oldStringLength, newString);
        }

        return result.toString();
    }

    // Just a copy of the parent's indexOf method, as it's private
    private static int indexOf(StringBuffer in, String str, int fromIndex)
    {
        if (in == null)
        {
            return -1;
        }

        if (str == null)
        {
            str = "";
        }

        int lengthIn = in.length();
        int lengthStr = str.length();

        if (lengthIn < lengthStr)
        {
            return -1;
        }

        if (fromIndex > lengthIn)
        {
            if (lengthIn == 0 && fromIndex == 0 && lengthStr == 0)
            {
                return 0;
            }
            return -1;
        }

        if (fromIndex < 0)
        {
            fromIndex = 0;
        }

        if (lengthStr == 0)
        {
            return fromIndex;
        }

        int strPos = 0;
        for (int i = fromIndex; i < lengthIn; i++)
        {
            if (in.charAt(i) == str.charAt(strPos))
            {
                strPos++;
                if(strPos == lengthStr)
                {
                    return i - lengthStr + 1;
                }
            }
            else
            {
                strPos = 0;
            }
        }

        return -1;
    }

}
